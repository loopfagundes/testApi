package testcases;

import framework.LoginBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTestCaseLogin extends LoginBaseTest {

    private static String token;

    @Description("Teste com o metodo de POSTAuthLogin para acessa login")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/login/login")
    @Feature("Login")
    @Feature("POST Auth Login")
    @Test
    public void POSTAuthLogin() {
        token =
                given()
                            .spec(requestSpecLogin)
                        .when()
                            .post()
                        .then()
                            .log().all()
                            .spec(responseSpecLogin)
                            .extract().path("data.attributes.auth-token");

        System.out.println("{{token}}: " + token);
    }

    @Description("Teste com o metodo de DELLogout para logout")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/login/logout")
    @Feature("Login")
    @Feature("DEL Logout")
    @Test
    public void DELLogout() {
        POSTAuthLogin();
        given()
                    .spec(requestSpecLogin)
                .when()
                    .delete("/" + token)
                .then()
                    .log().all()
                    .statusCode(204);
    }
}