package agibank.tech.test.parser;

import java.math.BigDecimal;

import agibank.tech.test.model.Salesman;
import agibank.tech.test.util.IdentityTypeEnum;

public class SalesmanParser {

	private static final String SEPARATOR = "ç";

    public static Salesman parse(String line) throws Exception {
        if(!line.startsWith(IdentityTypeEnum.SALESMAN.getId()))
            throw new Exception("Invalid identifier, line must start with: " + IdentityTypeEnum.SALESMAN.getId());

        String[] data = line.split(SEPARATOR);

        if(data.length != 4){
            throw new Exception("Invalid salesman information");
        }

        return new Salesman(data[1], data[2], new BigDecimal(data[3]));
    }
}