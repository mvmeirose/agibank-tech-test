package agibank.tech.test.parser;

import agibank.tech.test.model.Customer;

public class CustomerParser {

	public static Customer parse(String[] data) throws Exception {
        return new Customer(data[1], data[2], data[3]);
    }
}