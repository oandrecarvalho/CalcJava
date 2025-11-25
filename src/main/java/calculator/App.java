package calculator;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        double result = calc.calculate(10, 5, new Sum());
        System.out.println("Sum result: " + result);
    }
}
