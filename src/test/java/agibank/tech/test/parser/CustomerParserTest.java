package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import agibank.tech.test.model.Customer;
import agibank.tech.test.util.IdentityTypeEnum;

public class CustomerParserTest {

	@Test
    public void shouldParseSuccess() throws Exception {
        String line = "002Á2345675434544345ÁJose da SilvaÁRural";
        Customer c = CustomerParser.parse(line);

        assertTrue(c.getName().equals("Jose da Silva"));
        assertTrue(c.getCnpj().equals("2345675434544345"));
        assertTrue(c.getBusinessArea().equals("Rural"));
    }

    @Test
    public void shouldParseDataSizeException() throws Exception {
    	String line = "002Á2345675434544345ÁJose da Silva";
    	String message = "Invalid customer information";
    	try {
    		Customer c = CustomerParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }

    @Test
    public void shoulParseException() {
        String line = "004Á2345675434544345ÁJose da Silva";
        String message = "Invalid identifier, line must start with: " + IdentityTypeEnum.CUSTOMER.getId();
        try {
        	Customer c = CustomerParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }
}