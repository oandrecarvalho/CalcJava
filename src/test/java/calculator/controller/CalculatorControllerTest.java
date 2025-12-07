package calculator.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;

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

    @Test
    public void testExpressionSimple() {
        given()
                .queryParam("expression", "3+4*2")
                .when()
                .get("/calc/expr")
                .then()
                .statusCode(200)
                .body(is("11.0"));
    }

    @Test
    public void testExpressionParentheses() {
        given()
                .queryParam("expression", "(8-1)+(2+3)")
                .when()
                .get("/calc/expr")
                .then()
                .statusCode(200)
                .body(is("12.0"));
    }

    @Test
    public void testExpressionNestedParentheses() {
        given()
                .queryParam("expression", "((8-1)*2)+3")
                .when()
                .get("/calc/expr")
                .then()
                .statusCode(200)
                .body(is("17.0"));
    }

    @Test
    public void testExpressionInvalid() {
        given()
                .queryParam("expression", "7+abc")
                .when()
                .get("/calc/expr")
                .then()
                .statusCode(400)
                .body(containsString("Expressão inválida!"));
    }

    @Test
    public void testExpressionEmpty() {
        given()
                .queryParam("expression", "")
                .when()
                .get("/calc/expr")
                .then()
                .statusCode(400)
                .body(is("A expressão não pode estar vazia!"));
    }
}