package calculator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@QuarkusTest
class AppTest {

    /**
     * Fonte dos cenários de teste para o método main.
     */
    private static Stream<String> inputProvider() {
        return Stream.of(
                "10\n5\n1\n",   // soma
                "10\n0\n4\n",   // divisão por zero
                "10\n5\n9\n"    // operação inválida
        );
    }

    @ParameterizedTest
    @MethodSource("inputProvider")
    void testMain_shouldNotThrow(String simulatedInput) {
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    @Test
    void testPrivateConstructor_shouldNotThrow() throws Exception {
        var constructor = App.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertDoesNotThrow(() -> constructor.newInstance());
    }
}
