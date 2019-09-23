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

public class SaleItemParserTest {

	private List<String> lines = new ArrayList<>();

	@Before
	public void initialize() {
		Path resourceDirectory = Paths.get("src","test","resources", "SaleItemTest.dat");
        try (BufferedReader br = Files.newBufferedReader(resourceDirectory)) {
        	lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
    public void shouldParseSuccess() throws Exception {
        List<ItemSale> itens = SaleItemParser.parseSale(lines.get(0));

        ItemSale item1 = itens.get(0);
        assertTrue(item1.getId().equals(1L));
        assertTrue(item1.getQuantity().equals(new BigDecimal("10")));
        assertTrue(item1.getPrice().equals(new BigDecimal("100")));

        ItemSale item2 = itens.get(1);
        assertTrue(item2.getId().equals(2L));
        assertTrue(item2.getQuantity().equals(new BigDecimal("30")));
        assertTrue(item2.getPrice().equals(new BigDecimal("2.50")));

        ItemSale item3 = itens.get(2);
        assertTrue(item3.getId().equals(3L));
        assertTrue(item3.getQuantity().equals(new BigDecimal("40")));
        assertTrue(item3.getPrice().equals(new BigDecimal("3.10")));
    }

    @Test(expected = Exception.class)
    public void shouldParseDataSizeException() throws Exception {
    	List<ItemSale> itens = SaleItemParser.parseSale(lines.get(1));
    }

    @Test(expected = Exception.class)
    public void shoulParseException() throws Exception {
        List<ItemSale> itens = SaleItemParser.parseSale(lines.get(2));
    }
}
