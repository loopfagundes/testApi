package testcases;

import framework.ListaContatoBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ListaContatoTestCase extends ListaContatoBaseTest {

    @Description("Teste com o metodo de GETListaContato para buscar a lista de contato")
    @Issue("Link para solucao")
    @Feature("Contato")
    @Feature("GET Lista Contato")
    @Test
    public void GETListaContato() {
        given()
                    .spec(requestSpec)
                .when()
                    .get()
                .then()
                    .log()
                    .body()
                    .spec(responseSpec);
    }

    @Description("Teste com o metodo de POSTCriarContato para criar um contato.")
    @Issue("Link para solucao")
    @Feature("Contato")
    @Feature("POST Criar Contato")
    @Test
    public void POSTCriarContato() {
        Response payload =
                given()
                            .spec(requestSpecPost)
                        .when()
                            .post()
                        .then()
                            .log()
                            .body()
                            .spec(responseSpecPost)
                            .extract().response();

        id = payload.then().extract().path("data.id");

        PATCHEditarContato();
        DELDeleteContato();

        payload.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas" + File.separator + "dataContatoSchema.json"));
    }

    @Description("Teste com o metodo de PATCHEditarContato para editar alugns dados de contato")
    @Issue("Link para solucao")
    @Feature("Contato")
    @Feature("PATCH Editar Contato")
    @Test
    public void PATCHEditarContato() {
        given()
                    .spec(requestSpecPatch)
                .when()
                    .patch("/" + id)
                .then()
                    .log()
                    .body()
                    .spec(responseSpecPatch);
    }

    @Description("Teste com o metodo de DELContato para delete um contato")
    @Issue("Link para solucao")
    @Feature("Contato")
    @Feature("DEL Deletar Contato")
    @Test
    public void DELDeleteContato() {
        given()
                    .spec(requestSpec)
                .when()
                    .delete("/" + id)
                .then()
                    .log()
                    .body()
                    .statusCode(204);
    }
}