package br.com.gabrielvitors.rest_with_spring_boot_and_java;

import br.com.gabrielvitors.exceptions.ExceptionResponse;
import br.com.gabrielvitors.exceptions.UnsupportedMathOperationException;
import br.com.gabrielvitors.math.SimpleMath;
import br.com.gabrielvitors.numberConvert.NumberConvert;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
                    method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException(
                    "Please, set a numeric value");
        }

        return SimpleMath.sum(
                NumberConvert.convertToDouble(numberOne),
                NumberConvert.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException(
                    "Please, set a numeric value");
        }

        return SimpleMath.multiplication(
                NumberConvert.convertToDouble(numberOne),
                NumberConvert.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divide(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException(
                    "Please, set a numeric value");
        }

        if (NumberConvert.convertToDouble(numberTwo) == 0){
            throw new UnsupportedMathOperationException("Impossible divide by 0");
        }

        return SimpleMath.divide(
                NumberConvert.convertToDouble(numberOne),
                NumberConvert.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(
            @PathVariable(value = "number") String number
    ) throws Exception {

        if (!NumberConvert.isNumeric(number)) {
            throw new UnsupportedMathOperationException(
                    "Please, set a numeric value");
        }

        return Math.sqrt(NumberConvert.convertToDouble(number)) ;
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException(
                    "Please, set a numeric value");
        }

        return (NumberConvert.convertToDouble(numberOne) + NumberConvert.convertToDouble(numberTwo)) / 2;
    }


}
