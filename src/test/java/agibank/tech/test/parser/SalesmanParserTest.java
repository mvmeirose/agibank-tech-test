package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import agibank.tech.test.model.Salesman;
import agibank.tech.test.util.IdentityTypeEnum;

public class SalesmanParserTest {

	@Test
    public void shouldParseSuccess() throws Exception {
        String line = "001Á1234567891234ÁPedroÁ50000";
        Salesman s = SalesmanParser.parse(line);

        assertTrue(s.getName().equals("Pedro"));
        assertTrue(s.getCpf().equals("1234567891234"));
        assertTrue(s.getSalary().equals(new BigDecimal("50000")));
    }

    @Test
    public void shouldParseDataSizeException() throws Exception {
    	String line = "001Á1234567891234ÁPedro";
    	String message = "Invalid salesman information";
    	try {
    		Salesman s = SalesmanParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }

    @Test
    public void shoulParseException() {
        String line = "003Á1234567891234ÁPedroÁ50000";
        String message = "Invalid identifier, line must start with: " + IdentityTypeEnum.SALESMAN.getId();
        try {
        	Salesman s = SalesmanParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }
}