package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AuthenticationTestCase {

    @Description("O Metodo de GETBasicAuth basico para acessa login com sucesso")
    @Issue("https://restapi.wcaquino.me/basicauth")
    @Feature("Basic Auth")
    @Feature("GET Basic Auth")
    @Test
    public void GETBasicAuth() {
        given()
                .when()
                    .get("https://admin:senha@restapi.wcaquino.me/basicauth")
                .then()
                    .log().all()
                    .body("status", is("logado"));
    }

    @Description("O Metodo de GETBasicAuth2 para acessa login com sucesso")
    @Issue("https://restapi.wcaquino.me/basicauth")
    @Feature("Basic Auth")
    @Feature("GET Basic Auth 2")
    @Test
    public void GETBasicAuth2() {
        given()
                .auth().preemptive().basic("admin", "senha")
                .when()
                    .get("https://restapi.wcaquino.me/basicauth")
                .then()
                    .log().all()
                    .body("status", is("logado"));
    }
}