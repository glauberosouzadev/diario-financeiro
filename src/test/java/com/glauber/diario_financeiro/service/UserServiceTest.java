package com.glauber.diario_financeiro.service;

import com.glauber.diario_financeiro.factory.UserTestFactory;
import com.glauber.diario_financeiro.model.User;
import com.glauber.diario_financeiro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;


    @Test
    public void shouldCreateUser() {
        var userDto = UserTestFactory.createUserDto();
        var user = UserTestFactory.createUser(1L);

        when(repository.save(any(User.class))).thenReturn(user);

        var userCreated = service.createUser(userDto);

        assertEquals("Glauber", userCreated.getName());
        assertEquals("glaubero@gmail.com", userCreated.getEmail());
    }

    @Test
    public void shouldFindUserById() {
        var user = UserTestFactory.createUser(1L);
        var userDTO = UserTestFactory.createUserDto();

        when(repository.save(any(User.class))).thenReturn(user);
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        service.createUser(userDTO);
        var result = service.findUserById(1L);

        assertEquals("Glauber", result.getName());
    }

    @Test
    public void shouldUpdateUser() {
        var user = UserTestFactory.createUser(1L);
        var userDTO = UserTestFactory.createUserDto();
        userDTO.setName("Joao");

        when(repository.save(any(User.class))).thenReturn(user);
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        service.createUser(userDTO);
        service.updateUser(1L, userDTO);

        assertEquals("Joao", user.getName());
    }

    @Test
    public void shouldDeleteUser() {
        var user = UserTestFactory.createUser(1L);
        var userDTO = UserTestFactory.createUserDto();

        when(repository.save(any(User.class))).thenReturn(user);
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        service.createUser(userDTO);
        service.deleteUser(1L);

        assertEquals(0, repository.count());
    }
}