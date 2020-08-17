package utils;

import framework.LoginBaseTest;

import static io.restassured.RestAssured.given;

public class TokenUtils extends LoginBaseTest {

    public static String getToken() {
        requestLogin();
        return
                given()
                            .spec(requestSpecLogin)
                        .when()
                            .post()
                        .then()
                            .log().all()
                            .spec(responseSpecLogin)
                            .extract().path("data.attributes.auth-token");
    }
}