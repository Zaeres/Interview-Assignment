package tests;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DataLibrary;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardWorkflowTest {

    @Test
    @Order(1)
    public void createCard() {
        String cardName = "Prepare Interview Task";

        Response response = given()
            .queryParam("name", cardName)
            .queryParam("idList", DataLibrary.toDoID)
        .when()
            .post("/cards")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(cardName))
            .body("closed", equalTo(false))
            .body("desc", equalTo(""))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("idList", equalTo(DataLibrary.toDoID))
            .body("badges", notNullValue())
            .body("badges.attachments", equalTo(0))
            .body("badges.checkItems", equalTo(0))
            .body("badges.checkItemsChecked", equalTo(0))
            .body("badges.subscribed", equalTo(false))
            .body("cover", notNullValue())
            .body("cover.size", equalTo("normal"))
            .body("cover.brightness", equalTo("dark"))
            .body("url", containsString("https://trello.com/c/"))
            .body("shortUrl", containsString("https://trello.com/c/"))
            .body("isTemplate", equalTo(false))
            .extract()
            .response();

        DataLibrary.cardID = response.path("id");
        Assertions.assertNotNull(DataLibrary.cardID, "cardID is null");
        System.out.println("Card ID: " + DataLibrary.cardID);
    }

    @Test
    @Order(2)
    public void moveCardInProgress() {
        given()
            .pathParam("id", DataLibrary.cardID)
            .queryParam("idList", DataLibrary.inProgressID)
        .when()
            .put("/cards/{id}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("idList", equalTo(DataLibrary.inProgressID))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("closed", equalTo(false))
            .body("isTemplate", equalTo(false))
            .body("cover", notNullValue())
            .body("badges", notNullValue())
            .body("url", containsString("https://trello.com/c/"));

        given()
            .pathParam("id", DataLibrary.cardID)
        .when()
            .get("/cards/{id}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(DataLibrary.cardID))
            .body("idList", equalTo(DataLibrary.inProgressID))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("closed", equalTo(false))
            .body("isTemplate", equalTo(false))
            .body("url", containsString("https://trello.com/c/"));
    }

    @Test
    @Order(3)
    public void moveCardDone() {
        given()
            .pathParam("id", DataLibrary.cardID)
            .queryParam("idList", DataLibrary.doneID)
        .when()
            .put("/cards/{id}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("idList", equalTo(DataLibrary.doneID))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("closed", equalTo(false))
            .body("isTemplate", equalTo(false))
            .body("cover", notNullValue())
            .body("badges", notNullValue())
            .body("url", containsString("https://trello.com/c/"));

        given()
            .pathParam("id", DataLibrary.cardID)
        .when()
            .get("/cards/{id}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(DataLibrary.cardID))
            .body("idList", equalTo(DataLibrary.doneID))
            .body("idBoard", equalTo(DataLibrary.boardID))
            .body("closed", equalTo(false))
            .body("isTemplate", equalTo(false))
            .body("url", containsString("https://trello.com/c/"));
    }

    @Test
    @Order(4)
    public void addComment() {
        String commentText = "Testing Comment";

        given()
            .pathParam("id", DataLibrary.cardID)
            .queryParam("text", commentText)
        .when()
            .post("/cards/{id}/actions/comments")
        .then()
            .assertThat()
            .statusCode(200)
            .body("type", equalTo("commentCard"))
            .body("data.text", equalTo(commentText))
            .body("idMemberCreator", notNullValue())
            .body("memberCreator.username", notNullValue())
            .body("date", notNullValue());
    }

    @Test
    @Order(5)
    public void getComment() {
        given()
            .pathParam("id", DataLibrary.cardID)
        .when()
            .get("/cards/{id}/actions")
        .then()
            .assertThat()
            .statusCode(200)
            .body("[0].type", equalTo("commentCard"))
            .body("[0].data.text", equalTo("Testing Comment"))
            .body("[0].idMemberCreator", notNullValue())
            .body("[0].date", notNullValue());
    }
}
