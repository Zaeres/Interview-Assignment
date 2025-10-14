package tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DataLibrary;
import utils.ReadConfig;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardSetupTest
{
    @BeforeAll
    public static void readConfig()
    {
        RestAssured.baseURI = ReadConfig.get("base.url");
        RestAssured.requestSpecification = RestAssured
            .given()
            .queryParam("key", ReadConfig.get("api.key"))
            .queryParam("token", ReadConfig.get("api.token"));
    }

    @Test
    @Order(1)
    public void createBoard()
    {
        String boardName = "Interview Board";

        Response response = given()
            .queryParam("defaultLists", "false")
            .queryParam("name", (boardName))
        .when()
            .post("/boards")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(boardName))
            .body("closed", equalTo(false))
            .body("prefs.permissionLevel", equalTo("private"))
            .body("prefs.selfJoin", equalTo(true))
            .body("prefs.cardCovers", equalTo(true))
            .body("prefs.switcherViews", not(empty()))
            .body("labelNames", notNullValue())
            .body("url", containsString("https://trello.com/b/"))
            .extract()
            .response();
        DataLibrary.boardID = response.path("id");
        Assertions.assertNotNull(DataLibrary.boardID, "Board ID is null");
        System.out.println("Board ID: " + DataLibrary.boardID);
    }

    @Test
    @Order(2)
    public void createToDoList() {
        String listName = "To Do";

        Response response = given()
            .queryParam("name", listName)
            .queryParam("idBoard", DataLibrary.boardID)
        .when()
            .post("/lists")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(listName))
            .body("closed", equalTo(false))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("color", nullValue())
            .body("datasource.filter", equalTo(false))
            .body("limits", notNullValue())
            .extract()
            .response();
        DataLibrary.toDoID = response.path("id");
        Assertions.assertNotNull(DataLibrary.toDoID, "To Do List ID is null");
        System.out.println("To Do List ID: " + DataLibrary.toDoID);
    }

    @Test
    @Order(3)
    public void createInProgressList()
    {
        String listName = "In Progress";

        Response response = given()
            .queryParam("name", listName)
            .queryParam("idBoard", DataLibrary.boardID)
        .when()
            .post("/lists")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(listName))
            .body("closed", equalTo(false))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("color", nullValue())
            .body("datasource.filter", equalTo(false))
            .body("limits", notNullValue())
            .extract()
            .response();
        DataLibrary.inProgressID = response.path("id");
        Assertions.assertNotNull(DataLibrary.inProgressID, "In Progress List ID is null");
        System.out.println("In Progress List ID: " + DataLibrary.inProgressID);

    }

    @Test
    @Order(4)
    public void createDoneList()
    {
        String listName = "Done";

        Response response = given()
            .queryParam("name", listName)
            .queryParam("idBoard", DataLibrary.boardID)
        .when()
            .post("/lists")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(listName))
            .body("closed", equalTo(false))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("color", nullValue())
            .body("datasource.filter", equalTo(false))
            .body("limits", notNullValue())
            .extract()
            .response();
        DataLibrary.doneID = response.path("id");
        Assertions.assertNotNull(DataLibrary.doneID, "Done List ID is null");
        System.out.println("Done List ID: " + DataLibrary.doneID);
    }
}
