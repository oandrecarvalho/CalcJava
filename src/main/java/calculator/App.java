package calculator;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        OperationFactory factory = new OperationFactory();

        LOGGER.info("=== Calculator ===");

        // Entrada do usuário deve ser com System.out.print
        LOGGER.info("Digite o primeiro número: ");
        double a = scanner.nextDouble();

        LOGGER.info("Digite o segundo número: ");
        double b = scanner.nextDouble();

        LOGGER.info("\nEscolha a operação:");
        LOGGER.info("1 - Soma");
        LOGGER.info("2 - Subtração");
        LOGGER.info("3 - Multiplicação");
        LOGGER.info("4 - Divisão");
        LOGGER.info("Opção: ");

        int opcao = scanner.nextInt();

        try {
            Operation op = factory.getOperation(opcao, b);
            double result = calc.calculate(a, b, op);
            LOGGER.log(Level.INFO, "Resultado: {0}", result);

        } catch (Exception e) {
            LOGGER.severe("Erro: " + e.getMessage());
        }

        scanner.close();
    }
}
