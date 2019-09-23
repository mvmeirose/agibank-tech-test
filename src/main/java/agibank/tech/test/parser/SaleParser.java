package agibank.tech.test.parser;

import java.util.List;

import agibank.tech.test.model.Sale;
import agibank.tech.test.model.ItemSale;

public class SaleParser {

    public static Sale parse(String[] data) throws Exception {
        List<ItemSale> itens = SaleItemParser.parseSale(data[2]);
        Sale venda = new Sale(Long.parseLong(data[1]), itens, data[3]);
        venda.updateTotal();

        return venda;
    }
}