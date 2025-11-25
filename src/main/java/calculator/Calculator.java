package calculator;

public class Calculator {
    public double calculate(double a, double b, Operation operation) {
        return operation.execute(a, b);
    }
}
