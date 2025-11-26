package calculator;

public class InputParser {

    public double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("Entrada inválida, digite um número!");
        }
    }
}
