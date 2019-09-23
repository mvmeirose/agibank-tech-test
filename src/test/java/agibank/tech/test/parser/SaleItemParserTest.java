package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import agibank.tech.test.model.ItemSale;

public class SaleItemParserTest {

	@Test
    public void shouldParseSuccess() throws Exception {
        String line = "[1-10-100,2-30-2.50,3-40-3.10]";
        List<ItemSale> itens = SaleItemParser.parseSale(line);

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

    @Test
    public void shouldParseDataSizeException() throws Exception {
    	String line = "[1-10-100,2-30-2.50,3-40]";
    	String message = "Invalid sales item information";
    	try {
    		List<ItemSale> itens = SaleItemParser.parseSale(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }

    @Test
    public void shoulParseException() {
        String line = "";
        String message = "Sales item not found";
        try {
        	List<ItemSale> itens = SaleItemParser.parseSale(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }
}
