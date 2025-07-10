package com.glauber.diario_financeiro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glauber.diario_financeiro.BaseCompTest;
import com.glauber.diario_financeiro.factory.UserTestFactory;
import com.glauber.diario_financeiro.model.User;
import com.glauber.diario_financeiro.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseCompTest {
    private final String URL = "/api/users";
    private final String Insert_Into_Users_SQL = "db.sql/inert_into_user.sql";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUser() throws Exception {
        //GIVEN
        var user = UserTestFactory.createUser();
        var body = objectMapper.writeValueAsString(user);
        //WHEN
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated());
       // var userById = userService.findUserById(1L);

        //THEN
        //Assertions.assertEquals("Glauber", userById.getName());
    }

}