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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListaContatoTestCase {

    private static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    private static final String BASE_PATH = "/contacts";
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private static RequestSpecification requestSpecPost;
    private static ResponseSpecification responseSpecPost;

    @BeforeClass
    public void setUp() {
        requestListaContato();
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
                    .log().body()
                .spec(responseSpec);
    }
}