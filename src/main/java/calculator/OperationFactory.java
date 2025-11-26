package calculator;

public class OperationFactory {

    public Operation getOperation(int option, double b) {
        return switch (option) {
            case 1 -> new Sum();
            case 2 -> new Subtraction();
            case 3 -> new Mult();
            case 4 -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Divisão por zero!");
                }
                yield new Division();
            }
            default -> throw new IllegalArgumentException("Operação inválida!");
        };
    }
}
