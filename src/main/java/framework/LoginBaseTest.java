package framework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.LoginModel;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class LoginBaseTest {

    protected static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    protected static final String BASE_PATH_SESSIONS = "/sessions";
    protected static RequestSpecification requestSpecLogin;
    protected static ResponseSpecification responseSpecLogin;

    @BeforeClass
    public void setUp() {
        requestLogin();
    }

    public static void requestLogin() {
        Map<String, LoginModel> objectLogin = new HashMap<>();
        objectLogin.put("session", new LoginModel());

        requestSpecLogin = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_SESSIONS)
                .setBody(objectLogin)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecLogin = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}