package guru.qa.restbackend;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void configureBaseUrl() {

        Configuration.baseUrl = "http://localhost:8081/";
        RestAssured.baseURI = "http://localhost:8081/";

    }
}
