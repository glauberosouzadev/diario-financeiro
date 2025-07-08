package com.glauber.diario_financeiro.controller;

import com.glauber.diario_financeiro.DTO.TransactionDTO;
import com.glauber.diario_financeiro.model.Transaction;
import com.glauber.diario_financeiro.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        var transaction = transactionService.createTransaction(id, transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    public ResponseEntity<List<Transaction>> transactionByUserId(@PathVariable Long id) {
        var transactionsByUser = transactionService.findtransactionByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionsByUser);
    }
}