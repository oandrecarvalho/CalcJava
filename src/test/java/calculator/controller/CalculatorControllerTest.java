package calculator.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class CalculatorControllerTest {

    @Test
    void testSumSuccess() {
        given()
                .queryParam("valor1", 10)
                .queryParam("valor2", 5)
                .when()
                .get("/calc/sum")
                .then()
                .statusCode(200)
                .body(is("15.0"));
    }

    @Test
    void testSumMissingParams() {
        given()
                .queryParam("valor1", 10)
                .when()
                .get("/calc/sum")
                .then()
                .statusCode(400)
                .body(is(CalculatorController.REQUIRED_PARAM));
    }

    @Test
    void testSubtractionSuccess() {
        given()
                .queryParam("valor1", 10)
                .queryParam("valor2", 4)
                .when()
                .get("/calc/sub")
                .then()
                .statusCode(200)
                .body(is("6.0"));
    }

    @Test
    void testMultiplicationSuccess() {
        given()
                .queryParam("valor1", 7)
                .queryParam("valor2", 3)
                .when()
                .get("/calc/mult")
                .then()
                .statusCode(200)
                .body(is("21.0"));
    }

    @Test
    void testDivisionSuccess() {
        given()
                .queryParam("valor1", 20)
                .queryParam("valor2", 5)
                .when()
                .get("/calc/division")
                .then()
                .statusCode(200)
                .body(is("4.0"));
    }

    @Test
    void testDivisionMissingParams() {
        given()
                .queryParam("valor1", 10)
                .when()
                .get("/calc/division")
                .then()
                .statusCode(400)
                .body(is(CalculatorController.REQUIRED_PARAM));
    }

    @Test
    void testDivisionByZero() {
        given()
                .queryParam("valor1", 10)
                .queryParam("valor2", 0)
                .when()
                .get("/calc/division")
                .then()
                .statusCode(400)
                .body(is("Divisão por zero não é permitida!"));
    }
}