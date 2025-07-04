package com.glauber.diario_financeiro.service;

import com.glauber.diario_financeiro.DTO.TransactionDTO;
import com.glauber.diario_financeiro.model.Transaction;
import com.glauber.diario_financeiro.repository.TranscationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TranscationRepository transcationRepository;
    private final UserService userService;

    // TODO criar transação
    public Transaction createTransaction(Long userId, TransactionDTO transactionDTO) {
        var user = userService.findUserById(userId);
        var transaction = Transaction.builder()
                .transactionType(transactionDTO.getTransactionType())
                .value(transactionDTO.getValue())
                .date(transactionDTO.getDate())
                .category(transactionDTO.getCategory())
                .description(transactionDTO.getDescription())
                .user(user)
                .build();
        return transcationRepository.save(transaction);
    }

    // TODO listar transações através do usuário.
    public List<Transaction> findTransactionByUser(Long userId) {
        return transcationRepository.findUserById(userId);
    }

    // TODO Listar transações dentro de um período
    public List<Transaction> listTransactionByPeriod(Long userId, LocalDate start, LocalDate end) {
        return transcationRepository.findByUserIdAndDataBetween(userId, start, end);
    }

    // TODO Calcular transação
    public BigDecimal calculateTransactionBalance(Long userId) {
        var transactions = findTransactionByUser(userId);
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