package com.glauber.diario_financeiro.DTO;

import com.glauber.diario_financeiro.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {

    private TransactionType transactionType;
    private BigDecimal value;
    private LocalDate date;
    private String category;
    private String description;
}