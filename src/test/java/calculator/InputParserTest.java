package calculator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class InputParserTest {

    private final InputParser parser = new InputParser();

    @Test
    void testValidNumber() {
        assertEquals(10.5, parser.parseDouble("10.5"));
    }

    @Test
    void testNegativeNumber() {
        assertEquals(-3, parser.parseDouble("-3"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "#@$", "", "   ", "--1", "5..2"})
    void testInvalidInputs(String invalidInput) {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> parser.parseDouble(invalidInput));

        assertEquals("Entrada inválida, digite um número!", ex.getMessage());
    }
}
