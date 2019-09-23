package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import agibank.tech.test.model.Customer;

public class CustomerParserTest {
	
	@Before
	public void initialize() {
		List<String> lines  = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("//src//test/resources/CustomerTest.dat"))) {
        	lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
        String message = "Invalid identifier, line must start with: ";
        try {
        	Customer c = CustomerParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }
}