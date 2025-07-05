package com.glauber.diario_financeiro.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String name;
    private String email;
    private List<TransactionDTO> transactionDTOS;
}