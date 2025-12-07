package calculator.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class CalculatorControllerTest {
    public static final String REQUIRED_PARAM = "Parameters value1 and value2 are required!";

    @Test
    void testSumSuccess() {
        given()
                .queryParam("valor1", 10)
                .queryParam("valor2", 5)
                .when().get("/calc/sum")
                .then().statusCode(200)
                .body(is("15.0"));
    }

    @Test
    void testSumMissingValores() {
        given()
                .queryParam("valor1", 10)
                .when().get("/calc/sum")
                .then().statusCode(400)
                .body(is(REQUIRED_PARAM));
    }

    @Test
    void testSumNullValues() {
        given()
                .queryParam("valor1", "")
                .queryParam("valor2", "")
                .when().get("/calc/sum")
                .then().statusCode(400);
    }

    @Test
    void testSubSuccess() {
        given()
                .queryParam("valor1", 8)
                .queryParam("valor2", 3)
                .when().get("/calc/sub")
                .then().statusCode(200)
                .body(is("5.0"));
    }

    @Test
    void testSubMissingValues() {
        given()
                .queryParam("valor1", "")
                .queryParam("valor2", "")
                .when().get("/calc/sub")
                .then().statusCode(400);
    }

    @Test
    void testSubMissingValores() {
        given()
                .queryParam("valor1", 10)
                .when().get("/calc/sub")
                .then().statusCode(400);
    }

    @Test
    void testMultSuccess() {
        given()
                .queryParam("valor1", 7)
                .queryParam("valor2", 3)
                .when().get("/calc/mult")
                .then().statusCode(200)
                .body(is("21.0"));
    }

    @Test
    void testMultMissingValores() {
        given()
                .queryParam("valor1", 10)
                .when().get("/calc/mult")
                .then().statusCode(400);
    }

    @Test
    void testMultNullValues() {
        given()
                .queryParam("valor1", "")
                .queryParam("valor2", "")
                .when().get("/calc/sum")
                .then().statusCode(400);
    }

    @Test
    void testMultMissingFirstParam() {
        given()
                .queryParam("valor2", 10)
                .when().get("/calc/mult")
                .then().statusCode(400)
                .body(is(REQUIRED_PARAM));
    }

    @Test
    void testMultMissingSecondParam() {
        given()
                .queryParam("valor1", 10)
                .when().get("/calc/mult")
                .then().statusCode(400)
                .body(is(REQUIRED_PARAM));

    }


    @Test
    void testDivisionSuccess() {
        given()
                .queryParam("valor1", 20)
                .queryParam("valor2", 4)
                .when().get("/calc/division")
                .then().statusCode(200)
                .body(is("5.0"));
    }

    @Test
    void testDivisionMissingValores() {
        given()
                .queryParam("valor1", 20)
                .when().get("/calc/division")
                .then().statusCode(400);
    }

    @Test
    void testDivisionNullValues() {
        given()
                .queryParam("valor1", "")
                .queryParam("valor2", "")
                .when().get("/calc/division")
                .then().statusCode(400);
    }

    @Test
    void testDivisionByZero() {
        given()
                .queryParam("valor1", 20)
                .queryParam("valor2", 0)
                .when().get("/calc/division")
                .then().statusCode(400)
                .body(is("Divisão por zero não é permitida!"));
    }
}