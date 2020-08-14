package testcases;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AuthenticationTestCase {

    @Test
    public void GETBasicAuth() {
        given()
                .when()
                    .get("https://admin:senha@restapi.wcaquino.me/basicauth")
                .then()
                    .log().all()
                    .body("status", is("logado"));
    }

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