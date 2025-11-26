package calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        double result = calc.calculate(5, 3, new calculator.Subtraction());
        System.out.println("Sum result: " + result);
    }
}
