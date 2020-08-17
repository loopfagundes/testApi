package testcases;

import framework.UsuarioBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsuarioTestCase extends UsuarioBaseTest {

    @Description("Teste com o metodo de POSTCriarUsuario para criar um usuario")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/usuarios/criar-usario")
    @Feature("Usuario")
    @Feature("POST Criar Usuario")
    @Test
    public void POSTCriarUsuario() {
        given()
                    .spec(requestSpec)
                .when()
                    .post()
                .then()
                    .log().all()
                    .spec(responseSpec);
    }
}