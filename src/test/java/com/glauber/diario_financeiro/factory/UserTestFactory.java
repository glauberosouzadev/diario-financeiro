package com.glauber.diario_financeiro.factory;

import com.glauber.diario_financeiro.DTO.UserDTO;
import com.glauber.diario_financeiro.model.User;

import java.util.ArrayList;

public class UserTestFactory {
    public static User createUser(Long id) {
        var user = new User();
        user.setId(id);
        user.setName("Glauber");
        user.setEmail("glaubero@gmail.com");
        return user;
    }

    public static User createUser() {
        var user = new User();
        user.setName("Glauber");
        user.setEmail("glaubero@gmail.com");
        return user;
    }

    public static UserDTO createUserDto() {
        var dto = new UserDTO();
        dto.setName("Glauber");
        dto.setEmail("glaubero@gmail.com");
        dto.setTransactionDTOS(new ArrayList<>());
        return dto;
    }
}