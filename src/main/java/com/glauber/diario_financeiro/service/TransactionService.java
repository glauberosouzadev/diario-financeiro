package com.glauber.diario_financeiro.service;

import com.glauber.diario_financeiro.DTO.TransactionDTO;
import com.glauber.diario_financeiro.model.Transaction;
import com.glauber.diario_financeiro.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    // TODO criar transação
    public Transaction createTransaction(Long userId, TransactionDTO transactionDTO) {
        var user = userService.findUserById(userId);
        var transaction = Transaction.builder()
                .transactionType(transactionDTO.getTransactionType())
                .value(transactionDTO.getValue())
                .data(transactionDTO.getDate())
                .category(transactionDTO.getCategory())
                .description(transactionDTO.getDescription())
                .user(user)
                .build();
        return transactionRepository.save(transaction);
    }

    // TODO listar transações através do usuário.
    public List<Transaction> findtransactionByUserId(Long userId) {
        var userById = userService.findUserById(userId);
        if (userById == null) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return transactionRepository.findByUserId(userById.getId());
    }

    // TODO Listar transações dentro de um período
    public List<Transaction> listTransactionByPeriod(Long userId, LocalDate start, LocalDate end) {
        var user = userService.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return transactionRepository.findByUserIdAndDataBetween(user.getId(), start, end);
    }

    // TODO Calcular transação
    public BigDecimal calculateTransactionBalance(Long userId) {
        var transactions = findtransactionByUserId(userId);
        var balance = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == transaction.getTransactionType().ENTRY) {
                balance = transaction.getValue();
            } else {
                balance = balance.subtract(transaction.getValue());
            }
        }
        return balance;
    }
}