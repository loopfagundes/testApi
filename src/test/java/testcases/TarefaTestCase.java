package testcases;

import framework.TarefaBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TarefaTestCase extends TarefaBaseTest {

    @Description("Teste com o metodo de GETListarTarefas para buscar a lista das tarefas")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/tarefas/listar-tarefas")
    @Feature("Tarefa")
    @Feature("GET Listar Tarefas")
    @Test
    public void GETListarTarefas() {
        given()
                    .header("Authorization", token)
                .when()
                    .get(BASE_URI + BASE_PATH_TASKS)
                .then()
                    .log().all()
                    .statusCode(200);
    }

    @Description("Teste com o metodo de POSTCriarTarefa para criar uma tarefa")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/tarefas/criar-tarefas")
    @Feature("Tarefa")
    @Feature("POST Criar Tarefa")
    @Test
    public void POSTCriarTarefa() {
        Response payload =
            given()
                    .header("Authorization", token)
                    .spec(requestSpecTask)
                .when()
                    .post()
                .then()
                    .log().all()
                    .spec(responseSpecTask)
                    .extract().response();

        id = payload.then().extract().path("data.id");

        PATCHEditarTarefa();
        DELDeleteTarefa();
    }

    @Description("Teste com o metodo de PATCHEditarTarefa para editar uma tarefa")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/tarefas/editar-tarefas")
    @Feature("Tarefa")
    @Feature("PATCH Editar Tarefa")
    @Test
    public void PATCHEditarTarefa() {
        given()
                    .header("Authorization", token)
                    .spec(requestSpecPatch)
                .when()
                    .patch("/" + id)
                .then()
                    .log().all()
                    .spec(responseSpecPatch);
    }

    @Description("Teste com o metodo de DELTarefa para delete uma tarefa")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/tarefas/deletar-tarefas")
    @Feature("Tarefa")
    @Feature("DEL Delete Tarefa")
    @Test
    public void DELDeleteTarefa() {
        given()
                    .header("Authorization", token)
                    .spec(requestSpecTask)
                .when()
                    .delete("/" + id)
                .then()
                    .log().all()
                    .statusCode(204);
    }
}