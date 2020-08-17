package framework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CriarUsuarioModel;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class UsuarioBaseTest {

    protected static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    protected static final String BASE_PATH_USERS = "/users";
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        requestCriarUsuario();
    }

    public void requestCriarUsuario() {
        Map<String, CriarUsuarioModel> objectCriarUsuario = new HashMap<>();
        objectCriarUsuario.put("user", new CriarUsuarioModel());

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
}