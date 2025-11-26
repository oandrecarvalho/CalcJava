package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    // ---- SUM ----
    @Test
    void testSumExecute() {
        Sum sum = new Sum();
        assertEquals(7, sum.execute(3, 4));
        assertEquals(-1, sum.execute(2, -3));
    }

    // ---- SUBTRACTION ----
    @Test
    void testSubExecute() {
        Subtraction sub = new Subtraction();
        assertEquals(1, sub.execute(4, 3));
        assertEquals(-5, sub.execute(2, 7));
    }

    // ---- MULTIPLICATION ----
    @Test
    void testMultExecute() {
        Mult mult = new Mult();
        assertEquals(12, mult.execute(3, 4));
        assertEquals(-6, mult.execute(-2, 3));
    }

    // ---- DIVISION ----
    @Test
    void testDivideExecute() {
        Division div = new Division();
        assertEquals(2, div.execute(6, 3));
        assertEquals(-4, div.execute(8, -2));
    }

    @Test
    void testDivideByZero() {
        Division div = new Division();
        assertTrue(Double.isInfinite(div.execute(10, 0)));
    }
}
