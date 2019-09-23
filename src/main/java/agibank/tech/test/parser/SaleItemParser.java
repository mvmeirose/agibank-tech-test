package agibank.tech.test.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import agibank.tech.test.model.ItemSale;

@Component
public class SaleItemParser {

    private static final String SALE_SEPARATOR = ",";
    private static final String ITEM_SEPARATOR = "-";

    public static List<ItemSale> parseSale(String line) throws Exception {
        line = line.replace("[", "").replace("]", "");

        if(line.isBlank())
        	throw new Exception("Sales item not found");

        String[] data = line.split(SALE_SEPARATOR);

        List<ItemSale> itens = new ArrayList<ItemSale>();
        for (String item: data){
            itens.add(parseSaleItem(item));
        }

        return itens;
    }

    private static ItemSale parseSaleItem(String line) throws Exception {
    	String[] data = line.split(ITEM_SEPARATOR);

        if(data.length != 3){
            throw new Exception("Invalid sales item information");
        }

        return new ItemSale(Long.parseLong(data[0]), new BigDecimal(data[1]), new BigDecimal(data[2]));
    }
}