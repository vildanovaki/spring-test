package guru.qa.restbackend.tests;

import guru.qa.restbackend.TestBase;
import guru.qa.restbackend.domain.library.BookInfo;
import guru.qa.restbackend.domain.users.UserInfo;
import guru.qa.restbackend.lombok.request.*;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static guru.qa.restbackend.spec.Spec.requestBank;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LibraryControllerTests extends TestBase {

    public static AuthorRequestSuccessfulAdd authorRequestSuccessfulAdd = new AuthorRequestSuccessfulAdd();
    public static AuthorRequestNegativeAdd authorRequestNegativeAdd = new AuthorRequestNegativeAdd();
    public static BookRequestSuccessfulAdd bookRequestSuccessfulAdd = new BookRequestSuccessfulAdd();
    public static BookRequestNegativeAdd bookRequestNegativeAdd = new BookRequestNegativeAdd();
    public static BooksForOneAuthor booksForOneAuthor = new BooksForOneAuthor();

    @Test
    void addAuthorSuccessfulTest(){
        given()
                .contentType(JSON)
                .body(authorRequestSuccessfulAdd)
                .when()
                .put("add/author")
                .then()
                .statusCode(200)
                .body("author_name", is(authorRequestSuccessfulAdd.getAuthorName()));
    }

    @Test
    void addAuthorNegativeTest(){
        given()
                .contentType(JSON)
                .body(authorRequestNegativeAdd)
                .when()
                .put("add/author")
                .then()
                .statusCode(409);
    }

    @Test
    void addBookSuccessfulTest(){
        given()
                .contentType(JSON)
                .body(bookRequestSuccessfulAdd)
                .when()
                .put("add/book")
                .then()
                .statusCode(200)
                .body("book", is(bookRequestSuccessfulAdd.getBook()));
    }

    @Test
    void addBookNegativeTest(){
        given()
                .contentType(JSON)
                .body(bookRequestNegativeAdd)
                .when()
                .put("add/book")
                .then()
                .statusCode(409);
    }

    @Test
    void getAllBooksTest() {
        String[] bookInfos = given()
                .when()
                .get("get/booksAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(String[].class);
    }

    @Test
    void getBooksForOneAuthorTest(){
        given()
                .contentType(JSON)
                .body(booksForOneAuthor)
                .when()
                .post("get/allBooks/forOneAuthor")
                .then()
                .statusCode(200);
    }
}
