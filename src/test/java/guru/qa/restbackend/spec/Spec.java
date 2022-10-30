package guru.qa.restbackend.spec;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Spec {

    public static RequestSpecification requestBank =
            with()
                    .baseUri("http://localhost:8081")
                    .basePath("/");
}
