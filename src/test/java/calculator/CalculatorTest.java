package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {
    private final Calculator calc = new Calculator();

    @Test
    void testSum() {
        assertEquals(5, calc.calculate(2, 3, new Sum()));
    }
}
