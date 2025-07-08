package com.glauber.diario_financeiro.service;

import com.glauber.diario_financeiro.factory.TransactionTestFactory;
import com.glauber.diario_financeiro.factory.UserTestFactory;
import com.glauber.diario_financeiro.model.Transaction;
import com.glauber.diario_financeiro.model.User;
import com.glauber.diario_financeiro.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;
    @Mock
    private UserService userService;
    @InjectMocks
    private TransactionService service;

    @Test
    public void shouldCreateTransaction() {
        var transactionDto = TransactionTestFactory.createTransactionDto();
        var transaction = TransactionTestFactory.createTransaction(1L, transactionDto);
        var user = UserTestFactory.createUser(1L);

        when(repository.save(any(Transaction.class))).thenReturn(transaction);
        when(userService.findUserById(1L)).thenReturn(user);

        var transactionSaved = service.createTransaction(1L, transactionDto);

        assertEquals(1L, transactionSaved.getId());
        assertEquals("Monthly salary", transactionSaved.getDescription());
    }

    @Test
    public void shouldfindTransactionByUser() {
        var transactionDto = TransactionTestFactory.createTransactionDto();
        var transaction = TransactionTestFactory.createTransaction(1L, transactionDto);
        var user = UserTestFactory.createUser(1L);

        when(repository.findtransactionByUserId(1L)).thenReturn(List.of(transaction));
        when(userService.findUserById(1L)).thenReturn(user);
        var transactionsByUser = service.findtransactionByUserId(1L);

        assertFalse(transactionsByUser.isEmpty());
        assertEquals(1, transactionsByUser.size());
    }
    //TODO Finalizar o test!!!!
    @Test
    public void shouldListTransactionsByPeriod(){
        var transactionDto = TransactionTestFactory.createTransactionDto();
        var transaction = TransactionTestFactory.createTransaction(1L, transactionDto);
        var user = UserTestFactory.createUser(1L);

        when(repository.findByUserIdAndDataBetween(1L,  transactionDto.getDate().minusDays(1), transactionDto.getDate().plusDays(1)))
                .thenReturn(List.of(transaction));
        when(userService.findUserById(1L)).thenReturn(user);
        var transactionsByUser = service.listTransactionByPeriod(1L, transactionDto.getDate().minusDays(1), transactionDto.getDate().plusDays(1));

        assertEquals(1, transactionsByUser.size());

    }
    @Test
    public void shouldCalculateTransactionBalance(){
        var transactionDto = TransactionTestFactory.createTransactionDto();
        var transaction = TransactionTestFactory.createTransaction(1L, transactionDto);
        var user = UserTestFactory.createUser(1L);

        when(repository.findtransactionByUserId(1L)).thenReturn(List.of(transaction));
        when(userService.findUserById(1L)).thenReturn(user);
        var transactionsByUser = service.findtransactionByUserId(1L);
        BigDecimal bigDecimal = service.calculateTransactionBalance(1L);

        assertEquals(transaction.getValue(),bigDecimal);
        System.out.println("Saldo: " + bigDecimal);
    }
}