package agibank.tech.test.parser;

import agibank.tech.test.model.Customer;
import agibank.tech.test.util.IdentityTypeEnum;

public class CustomerParser {

	private static final String SEPARATOR = "ç";

	public static Customer parse(String line) throws Exception {

        if(!line.startsWith(IdentityTypeEnum.CUSTOMER.getId()))
            throw new Exception("Invalid identifier, line must start with: " + IdentityTypeEnum.CUSTOMER.getId());

        String[] data = line.split(SEPARATOR);

        if(data.length != 4){
            throw new Exception("Invalid customer information");
        }

        return new Customer(data[1], data[2], data[3]);
    }
}