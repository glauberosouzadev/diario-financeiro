package com.glauber.diario_financeiro.service;

import com.glauber.diario_financeiro.DTO.UserDTO;
import com.glauber.diario_financeiro.model.User;
import com.glauber.diario_financeiro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    //TODO função para criar usuário
    public User createUser(UserDTO userDTO) {
        var user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
        return userRepository.save(user);
    }

    //TODO localizar usuário pelo ID
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));
    }

    //TODO atualizar usuário
    public void updateUser(Long userId, UserDTO userDTO) {
        var userById = findUserById(userId);
        userById.setName(userDTO.getName());
        userById.setEmail(userDTO.getEmail());
        userRepository.save(userById);
    }

    //TODO remover usuário
    public void deleteUser(Long userId) {
        var userById = findUserById(userId);
        userRepository.delete(userById);
    }
}