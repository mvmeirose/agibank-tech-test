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

import agibank.tech.test.model.ItemSale;
import agibank.tech.test.model.Sale;

public class SaleParserTest {
	
	private List<String> lines = new ArrayList<>();
	
	@Before
	public void initialize() {
		Path resourceDirectory = Paths.get("src","test","resources", "SaleTest.dat");
        try (BufferedReader br = Files.newBufferedReader(resourceDirectory)) {
        	lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
    public void shouldParseSuccess() throws Exception {
        Sale v = SaleParser.parse(lines.get(0).split("ç"));
        
        assertTrue(lines.size() == 3);

        assertTrue(v.getSalesmanName().equals("Pedro"));
        assertTrue(v.getId().equals(10L));
        assertTrue(v.getItens().size() == 3);

        ItemSale item1 = v.getItens().get(0);
        assertTrue(item1.getId().equals(1L));
        assertTrue(item1.getQuantity().equals(new BigDecimal("10")));
        assertTrue(item1.getPrice().equals(new BigDecimal("100")));

        ItemSale item2 = v.getItens().get(1);
        assertTrue(item2.getId().equals(2L));
        assertTrue(item2.getQuantity().equals(new BigDecimal("30")));
        assertTrue(item2.getPrice().equals(new BigDecimal("2.50")));

        ItemSale item3 = v.getItens().get(2);
        assertTrue(item3.getId().equals(3L));
        assertTrue(item3.getQuantity().equals(new BigDecimal("40")));
        assertTrue(item3.getPrice().equals(new BigDecimal("3.10")));
    }

    @Test(expected = Exception.class)
    public void shouldParseDataSizeException() throws Exception {
    	Sale v = SaleParser.parse(lines.get(1).split("ç"));
    }

    @Test(expected = Exception.class)
    public void shoulParseException() throws Exception {
        Sale v = SaleParser.parse(lines.get(2).split("ç"));
    }
}