package agibank.tech.test.parser;

import java.util.List;

import org.springframework.stereotype.Component;

import agibank.tech.test.model.Sale;
import agibank.tech.test.model.ItemSale;

@Component
public class SaleParser {

    public static Sale parse(String[] data) throws Exception {
        List<ItemSale> itens = SaleItemParser.parseSale(data[2]);
        Sale sale = new Sale(Long.parseLong(data[1]), itens, data[3]);
        sale.updateTotal();

        return sale;
    }
}