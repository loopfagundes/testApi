package framework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CriarContatoModel;
import models.EditarContatoModel;
import org.testng.annotations.BeforeClass;

public class ListaContatoBaseTest {

    protected static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    protected static final String BASE_PATH = "/contacts";
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    protected static RequestSpecification requestSpecPost;
    protected static ResponseSpecification responseSpecPost;
    protected static RequestSpecification requestSpecPatch;
    protected static ResponseSpecification responseSpecPatch;
    protected CriarContatoModel criarContatoModel = new CriarContatoModel();
    protected EditarContatoModel editarContatoModel = new EditarContatoModel();
    protected static String id;


    @BeforeClass
    public void setUp() {
        requestListaContato();
        requestCriarContato();
        requestEditarContato();
    }

    public static void requestListaContato() {
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
                .setBody(criarContatoModel)
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
                .setBody(editarContatoModel)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecPatch = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}