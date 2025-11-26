package calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {

    private final InputStream systemInBackup = System.in;

    @BeforeEach
    void setUp() {
        // nada aqui
    }

    @AfterEach
    void tearDown() {
        // restaura System.in após cada teste
        System.setIn(systemInBackup);
    }

    /**
     * Fluxo normal: soma (opção 1)
     * Entrada simulada: primeiro número, segundo número, opção
     */
    @Test
    void testMain_flowSum_shouldNotThrow() {
        String simulatedInput = "10\n5\n1\n"; // a = 10, b = 5, opção soma
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    /**
     * Fluxo: divisão por zero — a implementação deve capturar/registrar o erro internamente,
     * portanto main não deve lançar exceção.
     */
    @Test
    void testMain_divisionByZero_shouldNotThrow() {
        String simulatedInput = "10\n0\n4\n"; // divide por zero (opção 4)
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    /**
     * Fluxo: operação inválida — OperationFactory deverá lançar, App captura, logo main não lança.
     */
    @Test
    void testMain_invalidOperation_shouldNotThrow() {
        String simulatedInput = "10\n5\n9\n"; // opção inválida (9)
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
