package com.glauber.diario_financeiro.repository;

import com.glauber.diario_financeiro.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TranscationRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findUserById(Long userId);
    List<Transaction> findByUserIdAndDataBetween(Long userId, LocalDate start, LocalDate end);
}