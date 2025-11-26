package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testCalculatorUsesOperation() {
        Calculator calc = new Calculator();
        double result = calc.calculate(10, 5, new Sum());
        assertEquals(15, result);
    }

    @Test
    void testCalculatorDifferentOperations() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.calculate(8, 3, new Subtraction()));
        assertEquals(24, calc.calculate(6, 4, new Mult()));
        assertEquals(4, calc.calculate(20, 5, new Division()));
    }
}
