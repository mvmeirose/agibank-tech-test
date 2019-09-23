package agibank.tech.test.parser;

import org.springframework.stereotype.Component;

import agibank.tech.test.model.Customer;

@Component
public class CustomerParser {

	public static Customer parse(String[] data) throws Exception {
        return new Customer(data[1], data[2], data[3]);
    }
}