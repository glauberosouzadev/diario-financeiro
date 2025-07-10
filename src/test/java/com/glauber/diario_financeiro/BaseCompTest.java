package com.glauber.diario_financeiro;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;


// Classe base com os componentes necessários para realizarmos os testes de integração.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DiarioFinanceiroApplication.class)
@ExtendWith({SpringExtension.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
// a instância da classe de teste não será recriada a cada teste
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseCompTest {
    @Autowired
    private DataSource dataSource;

    @BeforeAll
    void initDB() {
        var dataBaseSeeder = new ResourceDatabasePopulator();
        dataBaseSeeder.addScript(new ClassPathResource("db.sql/inert_into_user.sql"));
        dataBaseSeeder.execute(dataSource);
    }
}
