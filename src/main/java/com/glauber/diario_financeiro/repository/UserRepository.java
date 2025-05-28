package com.glauber.diario_financeiro.repository;

import com.glauber.diario_financeiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}