package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {
    private final Calculator calc = new Calculator();

    //Sum
    @Test
    void testSum() {
        assertEquals(5, calc.calculate(2, 3, new Sum()));
    }

    //Subtraction
    @Test
    void testSubtract() {
        assertEquals(2, calc.calculate(5, 3, new Subtraction()));
    }

    //Multiplication
    @Test
    void testMultiply() {
        assertEquals(20, calc.calculate(4, 5, new Mult()));
    }

    //Division
    @Test
    void testDivide() {
        assertEquals(10, calc.calculate(20, 2, new Division()));
    }


}
