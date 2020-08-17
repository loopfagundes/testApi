package framework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CriarTarefaModel;
import models.EditarTarefaModel;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static utils.TokenUtils.getToken;

public class TarefaBaseTest {
    protected static final String BASE_URI = "https://api-de-tarefas.herokuapp.com";
    protected static final String BASE_PATH_TASKS = "/tasks";
    protected static String token;
    protected RequestSpecification requestSpecTask;
    protected ResponseSpecification responseSpecTask;
    protected RequestSpecification requestSpecPatch;
    protected ResponseSpecification responseSpecPatch;
    protected static String id;

    @BeforeClass
    public void setUp() {
        requestCriarTarefa();
        requestEditarTarefa();
        token = getToken();
    }

    public void requestCriarTarefa() {
        Map<String, CriarTarefaModel> objectTask = new HashMap<>();
        objectTask.put("task", new CriarTarefaModel());

        requestSpecTask = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_TASKS)
                .setBody(objectTask)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecTask = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public void requestEditarTarefa() {
        Map<String, EditarTarefaModel> objectEdit = new HashMap<>();
        objectEdit.put("task", new EditarTarefaModel());

        requestSpecPatch = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH_TASKS)
                .setBody(objectEdit)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecPatch = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}