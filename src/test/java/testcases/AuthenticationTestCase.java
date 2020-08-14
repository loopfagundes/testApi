package testcases;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CriarUsuarioModel;
import models.LoginModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AuthenticationTestCase {

    private static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    private static final String BASE_PATH_USERS = "/users";
    private static final String BASE_PATH_SESSIONS = "/sessions";
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private CriarUsuarioModel criarUsuarioModel = new CriarUsuarioModel();
    private RequestSpecification requestSpecLogin;
    private ResponseSpecification responseSpecLogin;
    private LoginModel loginModel = new LoginModel();
    private static String token;

    @BeforeClass
    public void setUp() {
        requestCriarUsuario();
        requestLogin();
    }

    public void requestCriarUsuario() {
        Map<String, CriarUsuarioModel> objectCriarUsuario = new HashMap<>();
        objectCriarUsuario.put("user", criarUsuarioModel);

       requestSpec = new RequestSpecBuilder()
               .setBaseUri(BASE_URI)
               .setBasePath(BASE_PATH_USERS)
               .setBody(objectCriarUsuario)
               .setContentType(ContentType.JSON)
               .build();

       responseSpec = new ResponseSpecBuilder()
               .expectStatusCode(201)
               .expectContentType(ContentType.JSON)
               .build();
    }

    public void requestLogin() {
        Map<String, LoginModel> objectLogin = new HashMap<>();
        objectLogin.put("session", loginModel);

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