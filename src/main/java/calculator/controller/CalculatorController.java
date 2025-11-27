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
        Sum sum = new Sum();
        double result = sum.execute(valor1, valor2);
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
        Subtraction sub = new Subtraction();
        double result = sub.execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }

    @GET
    @Path("/mult")
    @Produces(MediaType.TEXT_PLAIN)
    public Response mult(@QueryParam("valor1") Double valor1,
                         @QueryParam("valor2") Double valor2) {
        if (valor1 == null || valor2 == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(REQUIRED_PARAM)
                    .build();
        }
        Mult mult = new Mult();
        double result = mult.execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }

    @GET
    @Path("/division")
    @Produces(MediaType.TEXT_PLAIN)
    public Response division(@QueryParam("valor1") Double valor1,
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
        Division division = new Division();
        double result = division.execute(valor1, valor2);
        return Response.ok(String.valueOf(result)).build();
    }
}