package calculator.controller;

import calculator.Division;
import calculator.Mult;
import calculator.Subtraction;
import calculator.Sum;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/calc")
public class CalculatorController {

    public static final String REQUIRED_PARAM = "Parameters value1 and value2 are required!";

    @GET
    @Path("/sum")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sum(
            @QueryParam("valor1") Double valor1,
            @QueryParam("valor2") Double valor2) {

        if (valor1 == null || valor2 == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(REQUIRED_PARAM)
                    .build();
        }

        double result = new Sum().execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }

    @GET
    @Path("/sub")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sub(
            @QueryParam("valor1") Double valor1,
            @QueryParam("valor2") Double valor2) {

        if (valor1 == null || valor2 == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(REQUIRED_PARAM)
                    .build();
        }

        double result = new Subtraction().execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }

    @GET
    @Path("/mult")
    @Produces(MediaType.TEXT_PLAIN)
    public Response mult(
            @QueryParam("valor1") Double valor1,
            @QueryParam("valor2") Double valor2) {

        if (valor1 == null || valor2 == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(REQUIRED_PARAM)
                    .build();
        }

        double result = new Mult().execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }

    @GET
    @Path("/division")
    @Produces(MediaType.TEXT_PLAIN)
    public Response division(
            @QueryParam("valor1") Double valor1,
            @QueryParam("valor2") Double valor2) {

        if (valor1 == null || valor2 == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(REQUIRED_PARAM)
                    .build();
        }

        if (valor2 == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Divisão por zero não é permitida!")
                    .build();
        }

        double result = new Division().execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }

    // --------------------------
    //      EXPRESSÕES
    // --------------------------

    @GET
    @Path("/expr")
    @Produces(MediaType.TEXT_PLAIN)
    public Response expr(@QueryParam("expression") String expression) {

        if (expression == null || expression.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("A expressão não pode estar vazia!")
                    .build();
        }

        try {
            // Normaliza caracteres
            String exp = expression
                    .replace("×", "*")
                    .replace("÷", "/")
                    .replace(",", ".")
                    .replace(" ", "");

            // Validação básica
            if (!exp.matches("[0-9+\\-*/().]+")) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Expressão inválida!")
                        .build();
            }

            // Calcula usando parser customizado
            double result = evaluateExpression(exp);

            return Response.ok(String.valueOf(result)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao calcular a expressão!")
                    .build();
        }
    }

    // --------------------------
    //   PARSER MATEMÁTICO
    // --------------------------

    private double evaluateExpression(String expr) {

        return new Object() {
            int pos = -1;
            int ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length())
                    throw new RuntimeException("Caractere inesperado");
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) {
                        double divisor = parseFactor();
                        if (divisor == 0) throw new RuntimeException("Divisão por zero!");
                        x /= divisor;
                    } else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = pos;

                if (eat('(')) {
                    x = parseExpression();
                    if (!eat(')'))
                        throw new RuntimeException("Parêntese esperado");
                }
                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, pos));
                }
                else {
                    throw new RuntimeException("Número inválido");
                }

                return x;
            }

        }.parse();
    }
}