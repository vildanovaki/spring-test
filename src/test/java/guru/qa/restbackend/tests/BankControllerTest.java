package guru.qa.restbackend.tests;

import guru.qa.restbackend.TestBase;
import guru.qa.restbackend.domain.users.UserInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static guru.qa.restbackend.spec.Spec.requestBank;
import static io.restassured.RestAssured.*;

public class BankControllerTest extends TestBase {


    @Test
    void bankControllerTest() {
        UserInfo[] userInfos = given()
                .when()
                .get("user/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(UserInfo[].class);

        Stream.of(userInfos)
                .filter(userInfo -> userInfo.getUserName().equals("Dima"))
                .peek(userInfo -> System.out.println(userInfo.getUserName()))
                .map(userInfo -> userInfo.toString())
                .collect(Collectors.toList());
    }
}
