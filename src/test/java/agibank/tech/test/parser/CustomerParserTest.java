package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import agibank.tech.test.model.Customer;

public class CustomerParserTest {
	
	List<String> lines = new ArrayList<>();
	
	@Before
	public void initialize() {
		Path resourceDirectory = Paths.get("src","test","resources", "CustomerTest.dat");
        try (BufferedReader br = Files.newBufferedReader(resourceDirectory)) {
        	lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
    public void shouldParseSuccess() throws Exception {
        Customer c = CustomerParser.parse(lines.get(0).split("ç"));

        assertTrue(c.getName().equals("Jose da Silva"));
        assertTrue(c.getCnpj().equals("2345675434544345"));
        assertTrue(c.getBusinessArea().equals("Rural"));
    }

    @Test(expected = Exception.class)
    public void shouldParseDataSizeException() throws Exception {
    	Customer c = CustomerParser.parse(lines.get(1).split("ç"));
    }

    @Test(expected = Exception.class)
    public void shoulParseException() throws Exception {
    	Customer c = CustomerParser.parse(lines.get(2).split("ç"));
    }
}