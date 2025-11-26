package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationFactoryTest {

    private final OperationFactory factory = new OperationFactory();

    @Test
    void testSumOperation() {
        Operation op = factory.getOperation(1, 10);
        assertTrue(op instanceof Sum);
    }

    @Test
    void testSubtractionOperation() {
        Operation op = factory.getOperation(2, 10);
        assertTrue(op instanceof Subtraction);
    }

    @Test
    void testMultiplicationOperation() {
        Operation op = factory.getOperation(3, 10);
        assertTrue(op instanceof Mult);
    }

    @Test
    void testDivisionOperation() {
        Operation op = factory.getOperation(4, 5);
        assertTrue(op instanceof Division);
    }

    @Test
    void testDivisionByZeroThrowsError() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> factory.getOperation(4, 0));
        assertEquals("Divisão por zero!", ex.getMessage());
    }

    @Test
    void testInvalidOperationNumberThrowsError() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> factory.getOperation(9, 10));
        assertEquals("Operação inválida!", ex.getMessage());
    }
}
