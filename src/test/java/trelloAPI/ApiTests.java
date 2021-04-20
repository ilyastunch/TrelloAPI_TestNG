package trelloAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTests {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = ConfigurationReader.getProperty("baseURI");
        RestAssured.basePath = ConfigurationReader.getProperty("basePath");
    }


    public String createBoardAndListThenGetTheListID(){
        String boardId =
                given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", ConfigurationReader.getProperty("key"))
                    .queryParam("token", ConfigurationReader.getProperty("token"))
                    .queryParam("name", "BoardTest")
                    .post("/boards").
                    then()
                    .statusCode(200)
                    .contentType(ContentType.JSON).
                    assertThat()
                    .body("name", equalTo("BoardTest"))
                    .extract().path("id");

        String listId =
             given()
                .contentType("application/json")
                .when()
                    .queryParam("key", ConfigurationReader.getProperty("key"))
                    .queryParam("token", ConfigurationReader.getProperty("token"))
                    .queryParam("name","TestList")
                    .post("/boards/"+boardId+"/lists")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                .assertThat()
                    .body("name", equalTo("TestList"))
                    .extract().path("id");
        return listId;
    }

    @DisplayName("CreateBoard")
    @Test
    public void CreateBoard(){
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("name", "BoardTest")
                .post("/boards").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                assertThat()
                .body("name", equalTo("BoardTest"));
    }

    @DisplayName("CreateListOnTheBoard")
    @Test
    public void CreateListOnTheBoard(){
        String boardId =
                given()
                        .contentType("application/json").
                        when()
                        .queryParam("key", ConfigurationReader.getProperty("key"))
                        .queryParam("token", ConfigurationReader.getProperty("token"))
                        .queryParam("name", "BoardTest")
                        .post("/boards").
                        then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).
                        assertThat()
                        .body("name", equalTo("BoardTest"))
                        .extract().path("id");

                given()
                        .contentType("application/json")
                        .when()
                        .queryParam("key", ConfigurationReader.getProperty("key"))
                        .queryParam("token", ConfigurationReader.getProperty("token"))
                        .queryParam("name","ListTest")
                        .post("/boards/"+boardId+"/lists")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .assertThat()
                        .body("name", equalTo("ListTest"));
    }

    @DisplayName("CreateCardsAndEditDeleteAndAddComment")
    @Test
    public void CreateCardsAndEditDeleteAndAddComment(){
        String listId = createBoardAndListThenGetTheListID();
        String[] cardsIdArr = new String[3];

        // create 3 cards
        for (int i = 0; i < 3; i++) {
            cardsIdArr[i] = given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", ConfigurationReader.getProperty("key"))
                    .queryParam("token", ConfigurationReader.getProperty("token"))
                    .queryParam("name","newCard"+i)
                    .queryParam("idList",listId)
                    .queryParam("desc","initial Test Description")
                    .post("/cards").
                    then()
                    .statusCode(200)
                    .contentType(ContentType.JSON).
                    assertThat()
                    .body("name", equalTo("newCard"+i))
                    .extract().path("id");
        }

        //Edit one of the cards
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("name","newCard0")
                .queryParam("desc","edited Test Description")
                .put("/cards/"+cardsIdArr[0]).
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                assertThat()
                .body("desc", equalTo("edited Test Description"))
                .extract().path("id");

        //Delete one of the cards
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .delete("/cards/"+cardsIdArr[1]).
                then()
                .statusCode(200);

        //Add a comment to one of the cards
        given()
                .contentType("application/json").
                when()
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("text","testComment")
                .post("/cards/"+cardsIdArr[2]+"/actions/comments").
                then()
                .statusCode(200);
    }
}
