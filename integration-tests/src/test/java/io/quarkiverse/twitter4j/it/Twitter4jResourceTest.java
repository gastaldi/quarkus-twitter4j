package io.quarkiverse.twitter4j.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Twitter4jResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/twitter4j")
                .then()
                .statusCode(200)
                .body(is("Hello twitter4j"));
    }
}
