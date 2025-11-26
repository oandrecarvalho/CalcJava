package calculator;

import java.util.Scanner;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        OperationFactory factory = new OperationFactory();

        LOGGER.info("=== Calculator ===");

        // Entrada do usuário deve ser com System.out.print
        System.out.print("Digite o primeiro número: ");
        double a = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double b = scanner.nextDouble();

        System.out.println("\nEscolha a operação:");
        System.out.println("1 - Soma");
        System.out.println("2 - Subtração");
        System.out.println("3 - Multiplicação");
        System.out.println("4 - Divisão");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();

        try {
            Operation op = factory.getOperation(opcao, b);
            double result = calc.calculate(a, b, op);
            LOGGER.info("Resultado: " + result);

        } catch (Exception e) {
            LOGGER.severe("Erro: " + e.getMessage());
        }

        scanner.close();
    }
}
