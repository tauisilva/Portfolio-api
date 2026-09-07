package dev.taui.portfolio_api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
class ProjectResourceTest {

    @Test
    void testListProjectsEndpoint() {
        given()
                .when().get("/api/projects")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void testGetProjectByIdEndpoint() {
        given()
                .when().get("/api/projects/converter")
                .then()
                .statusCode(200)
                .body("title", is("Currency Converter"));
    }
}
