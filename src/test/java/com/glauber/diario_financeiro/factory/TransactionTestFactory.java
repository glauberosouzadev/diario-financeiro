package com.glauber.diario_financeiro.factory;

import com.glauber.diario_financeiro.DTO.TransactionDTO;
import com.glauber.diario_financeiro.model.Transaction;
import com.glauber.diario_financeiro.model.TransactionType;

public class TransactionTestFactory {

    public static Transaction createTransaction(Long id, TransactionDTO dto) {
        var transaction = new Transaction();
        transaction.setId(id);
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setValue(dto.getValue());
        transaction.setData(dto.getDate());
        transaction.setCategory(dto.getCategory());
        transaction.setDescription(dto.getDescription());
        return transaction;
    }

    public static TransactionDTO createTransactionDto() {
        var transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionType(TransactionType.ENTRY);
        transactionDTO.setValue(new java.math.BigDecimal("100.00"));
        transactionDTO.setDate(java.time.LocalDate.now());
        transactionDTO.setCategory("Salary");
        transactionDTO.setDescription("Monthly salary");
        return transactionDTO;
    }
}