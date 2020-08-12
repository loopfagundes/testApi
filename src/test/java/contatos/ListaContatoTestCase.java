package contatos;

import framework.BaseTestFW;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CriarContato;
import models.EditarContato;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListaContatoTestCase {

    private static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    private static final String BASE_PATH = "/contacts";
    private CriarContato criarContato = new CriarContato();
    private EditarContato editarContato = new EditarContato();
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private static RequestSpecification requestSpecPost;
    private static ResponseSpecification responseSpecPost;
    private static RequestSpecification requestSpecPatch;
    private static ResponseSpecification responseSpecPatch;

    @BeforeClass
    public void setUp() {
        requestListaContato();
        requestCriarContato();
        requestEditarContato();
    }

    public void requestListaContato() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public void requestCriarContato() {
        requestSpecPost = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setBody(criarContato)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecPost = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public void requestEditarContato() {
        requestSpecPatch = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setBody(editarContato)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecPatch = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Description("Teste com o metodo de GETListaContato para buscar a lista de contato")
    @Issue("Link para solucao")
    @Feature("Usuario")
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

    @Description("Teste com o metodo de POSTCriarContato para criar um contato")
    @Issue("Link para solucao")
    @Feature("Usuario")
    @Test
    public void POSTCriarContato() {
        String id =
            given()
                        .spec(requestSpecPost)
                    .when()
                        .post()
                    .then()
                        .log()
                        .body()
                        .spec(responseSpecPost)
                        .extract().path("data.id");

        PATCHEditarContato(id);
    }

    @Description("Teste com o metodo de PATCHEditarContato para editar alugns dados de contato")
    @Issue("Link para solucao")
    @Feature("Usuario")
    @Test
    public void PATCHEditarContato(String id) {
        given()
                    .spec(requestSpecPatch)
                .when()
                    .patch("/" + id)
                .then()
                    .log()
                    .body()
                    .spec(responseSpecPatch);
    }
}