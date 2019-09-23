package agibank.tech.test.parser;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import agibank.tech.test.model.ItemSale;
import agibank.tech.test.model.Sale;
import agibank.tech.test.util.IdentityTypeEnum;

public class SaleParserTest {

	@Test
    public void shouldParseSuccess() throws Exception {
        String line = "003Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁPedro";
        Sale v = SaleParser.parse(line);

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

    @Test
    public void shouldParseDataSizeException() throws Exception {
    	String line = "003Á10Á[1-10-100,2-30-2.50,3-40-3.10]";
    	String message = "Invalid sale information";
    	try {
    		Sale v = SaleParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }

    @Test
    public void shoulParseException() {
        String line = "004Á10Á[1-10-100,2-30-2.50,3-40-3.10]ÁPedro";
        String message = "Invalid identifier, line must start with: " + IdentityTypeEnum.SALE.getId();
        try {
        	Sale v = SaleParser.parse(line);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }
}
