package tests;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DataLibrary;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChecklistTest {

    @Test
    @Order(1)
    public void createPreparationChecklist() {
        String checklistName = "Preparation Steps";
        String checklistItemName_1 = "Checklist Item 1";
        String checklistItemName_2 = "Checklist Item 2";
        String checklistItemName_3 = "Checklist Item 3";

        Response response_checklist = given()
            .queryParam("idCard", DataLibrary.cardID)
            .queryParam("name", checklistName)
        .when()
            .post("/checklists")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(checklistName))
            .body("idCard", equalTo(DataLibrary.cardID))
            .body("checkItems", empty())
            .extract()
            .response();
        DataLibrary.preparationChecklistID = response_checklist.path("id");
        System.out.println("Preparation CheckListID: " + DataLibrary.preparationChecklistID);

        //Create "Preparation Steps" Item 1
        Response response_checklist_item_1 = given()
            .pathParam("id", DataLibrary.preparationChecklistID)
            .queryParam("name", checklistItemName_1)
        .when()
            .post("/checklists/{id}/checkItems")
        .then()
            .assertThat()
            .statusCode(200)
            .body("idChecklist", equalTo(DataLibrary.preparationChecklistID))
            .body("name", equalTo(checklistItemName_1))
            .body("state", equalTo("incomplete"))
            .extract()
            .response();
        DataLibrary.preparationChecklistItemID_1 = response_checklist_item_1.path("id");

        //Create "Preparation Steps" Item 2
        Response response_checklist_item_2 = given()
            .pathParam("id", DataLibrary.preparationChecklistID)
            .queryParam("name", checklistItemName_2)
        .when()
            .post("/checklists/{id}/checkItems")
        .then()
            .assertThat()
            .statusCode(200)
            .body("idChecklist", equalTo(DataLibrary.preparationChecklistID))
            .body("name", equalTo(checklistItemName_2))
            .body("state", equalTo("incomplete"))
            .extract()
            .response();
        DataLibrary.preparationChecklistItemID_2 = response_checklist_item_2.path("id");

        //Create "Preparation Steps" Item 3
        Response response_checklist_item_3 = given()
            .pathParam("id", DataLibrary.preparationChecklistID)
            .queryParam("name", checklistItemName_3)
        .when()
            .post("/checklists/{id}/checkItems")
        .then()
            .assertThat()
            .statusCode(200)
            .body("idChecklist", equalTo(DataLibrary.preparationChecklistID))
            .body("name", equalTo(checklistItemName_3))
            .body("state", equalTo("incomplete"))
            .extract()
            .response();
        DataLibrary.preparationChecklistItemID_3 = response_checklist_item_3.path("id");

        //Mark Checklist item 1
        given()
            .pathParam("id", DataLibrary.cardID)
            .pathParam("idCheckItem", DataLibrary.preparationChecklistItemID_1)
            .queryParam("state", "complete")
        .when()
            .put("/cards/{id}/checkItem/{idCheckItem}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("state", equalTo("complete"))
            .body("idChecklist", equalTo(DataLibrary.preparationChecklistID));

        //Mark Checklist item 2
        given()
            .pathParam("id", DataLibrary.cardID)
            .pathParam("idCheckItem", DataLibrary.preparationChecklistItemID_2)
            .queryParam("state", "complete")
        .when()
            .put("/cards/{id}/checkItem/{idCheckItem}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("state", equalTo("complete"))
            .body("idChecklist", equalTo(DataLibrary.preparationChecklistID));

        //Mark Checklist item 3
        given()
            .pathParam("id", DataLibrary.cardID)
            .pathParam("idCheckItem", DataLibrary.preparationChecklistItemID_3)
            .queryParam("state", "complete")
        .when()
            .put("/cards/{id}/checkItem/{idCheckItem}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("state", equalTo("complete"))
            .body("idChecklist", equalTo(DataLibrary.preparationChecklistID));
    }

    @Test
    @Order(2)
    public void createReviewChecklist() {
        String checklistName = "Review Steps";
        String checklistItemName_1 = "Checklist Item 1";
        String checklistItemName_2 = "Checklist Item 2";
        String checklistItemName_3 = "Checklist Item 3";

        Response response_checklist = given()
            .queryParam("name", checklistName)
            .queryParam("idCard", DataLibrary.cardID)
        .when()
            .post("/checklists")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo(checklistName))
            .body("idCard", equalTo(DataLibrary.cardID))
            .extract()
            .response();
        DataLibrary.reviewChecklistID = response_checklist.path("id");

        //Create "Review Steps" item 1
        Response response_checklist_item_1 = given()
            .pathParam("id", DataLibrary.reviewChecklistID)
            .queryParam("name", checklistItemName_1)
        .when()
            .post("/checklists/{id}/checkItems")
        .then()
            .assertThat()
            .statusCode(200)
            .body("idChecklist", equalTo(DataLibrary.reviewChecklistID))
            .body("name", equalTo(checklistItemName_1))
            .body("state", equalTo("incomplete"))
            .extract()
            .response();
        DataLibrary.reviewChecklistItemID_1 = response_checklist_item_1.path("id");

        //Create "Review Steps" item 2
        Response response_checklist_item_2 = given()
            .pathParam("id", DataLibrary.reviewChecklistID)
            .queryParam("name", checklistItemName_2)
        .when()
            .post("/checklists/{id}/checkItems")
        .then()
            .assertThat()
            .statusCode(200)
            .body("idChecklist", equalTo(DataLibrary.reviewChecklistID))
            .body("name", equalTo(checklistItemName_2))
            .body("state", equalTo("incomplete"))
            .extract()
            .response();
        DataLibrary.reviewChecklistItemID_2 = response_checklist_item_2.path("id");

        //Create "Review Steps" item 3
        Response response_checklist_item_3 = given()
            .pathParam("id", DataLibrary.reviewChecklistID)
            .queryParam("name", checklistItemName_3)
        .when()
            .post("/checklists/{id}/checkItems")
        .then()
            .assertThat()
            .statusCode(200)
            .body("idChecklist", equalTo(DataLibrary.reviewChecklistID))
            .body("name", equalTo(checklistItemName_3))
            .body("state", equalTo("incomplete"))
            .extract()
            .response();
        DataLibrary.reviewChecklistItemID_3 = response_checklist_item_3.path("id");

        //Mark Checklist item 1
        given()
            .pathParam("id", DataLibrary.cardID)
            .pathParam("idCheckItem", DataLibrary.reviewChecklistItemID_1)
            .queryParam("state", "complete")
        .when()
            .put("/cards/{id}/checkItem/{idCheckItem}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("state", equalTo("complete"))
            .body("idChecklist", equalTo(DataLibrary.reviewChecklistID));

        //Mark Checklist item 2
        given()
            .pathParam("id", DataLibrary.cardID)
            .pathParam("idCheckItem", DataLibrary.reviewChecklistItemID_2)
            .queryParam("state", "complete")
        .when()
            .put("/cards/{id}/checkItem/{idCheckItem}")
        .then()
            .assertThat()
            .statusCode(200)
            .body("state", equalTo("complete"))
            .body("idChecklist", equalTo(DataLibrary.reviewChecklistID));

    }

    @Test
    @Order(3)
    public void validateCheckListsAndItems() {
        given()
            .pathParam("id", DataLibrary.cardID)
        .when()
            .get("/cards/{id}/checklists")
        .then()
            .assertThat()
            .statusCode(200)
            .body("name", hasItems("Preparation Steps", "Review Steps"))
            .body("find { it.name == 'Preparation Steps' }.checkItems.state", everyItem(equalTo("complete")))
            .body("find { it.name == 'Review Steps' }.checkItems.state", hasItem("incomplete"))
            .body("find { it.name == 'Preparation Steps' }.checkItems.size()", greaterThanOrEqualTo(2))
            .body("find { it.name == 'Review Steps' }.checkItems.size()", greaterThanOrEqualTo(2));
    }
}
