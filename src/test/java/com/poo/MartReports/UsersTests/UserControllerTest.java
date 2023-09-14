package com.poo.MartReports.UsersTests;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.poo.MartReports.Controllers.UserController;
import com.poo.MartReports.Exceptions.UserNotFoundException;
import com.poo.MartReports.Models.User;
import com.poo.MartReports.Services.UserService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.userController);
    }

    // @Test
    // public void testUserRegister() {
    //     // Crie um objeto User para ser enviado na requisição POST
    //     User u = new User(1L, "Júlio", "a@a.com", "12345");

    //     // Defina as expectativas de comportamento do serviço UserService mockado
    //     when(userService.registerUser(u)).thenReturn(u); // Isso é um exemplo, ajuste conforme a lógica real

    //     // Execute a requisição POST e verifique o status de resposta
    //     given()
    //         .contentType(ContentType.JSON)
    //         .header("Cache-Control", "no-cache") // Desativa o cache
    //         .body(u)
    //     .when()
    //         .post("/users")
    //     .then()
    //         .statusCode(201);
    // }

    @Test
    public void testUserFindById() {
        try {
            when(this.userService.getUserById(1L)).thenReturn(new User(1L, "Torres", "a@a.com", "123"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/users/{id}", 1L).then().statusCode(200);
    }
}
