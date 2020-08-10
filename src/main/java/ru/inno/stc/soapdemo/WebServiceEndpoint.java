package ru.inno.stc.soapdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.inno.stc.types.calculator.AdditionInput;
import ru.inno.stc.types.calculator.ObjectFactory;
import ru.inno.stc.types.calculator.Output;
import ru.inno.stc.types.calculator.SubtractionInput;

@Endpoint
public class WebServiceEndpoint {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponsePayload
    @PayloadRoot(namespace = "http://stc.inno.ru/types/calculator", localPart = "AdditionInput")
    public Output addition(@RequestPayload AdditionInput input){
        logger.info("Запрос на сложение чисел {} и {} ", input.getNumber1(), input.getNumber2());
        ObjectFactory objectFactory = new ObjectFactory();
        Output        output        = objectFactory.createOutput();
        output.setResult(input.getNumber1() + input.getNumber2());
        return output;
    }

    @ResponsePayload
    @PayloadRoot(namespace = "http://stc.inno.ru/types/calculator", localPart = "SubtractionInput")
    public Output subtraction(@RequestPayload SubtractionInput input){
        logger.info("Запрос на вычитание чисел {} и {} ", input.getNumber1(), input.getNumber2());
        ObjectFactory objectFactory = new ObjectFactory();
        Output output = objectFactory.createOutput();
        output.setResult(input.getNumber1() - input.getNumber2());
        return output;
    }

}