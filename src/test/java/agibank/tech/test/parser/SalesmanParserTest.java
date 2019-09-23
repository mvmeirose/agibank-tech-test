package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import agibank.tech.test.model.Salesman;

public class SalesmanParserTest {

	List<String> lines = new ArrayList<>();

	@Before
	public void initialize() {
		Path resourceDirectory = Paths.get("src","test","resources", "SalesmanTest.dat");
        try (BufferedReader br = Files.newBufferedReader(resourceDirectory)) {
        	lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
    public void shouldParseSuccess() throws Exception {
        Salesman s = SalesmanParser.parse(lines.get(0).split("ç"));

        assertTrue(s.getName().equals("Pedro"));
        assertTrue(s.getCpf().equals("1234567891234"));
        assertTrue(s.getSalary().equals(new BigDecimal("50000")));
    }

    @Test(expected = Exception.class)
    public void shouldParseDataSizeException() throws Exception {
    	Salesman s = SalesmanParser.parse(lines.get(1).split("ç"));
    }

    @Test(expected = Exception.class)
    public void shoulParseException() throws Exception {
    	Salesman s = SalesmanParser.parse(lines.get(2).split("ç"));
    }
}