package agibank.tech.test.parser;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import agibank.tech.test.model.Salesman;

@Component
public class SalesmanParser {

    public static Salesman parse(String[] data) throws Exception {
        return new Salesman(data[1], data[2], new BigDecimal(data[3]));
    }
}