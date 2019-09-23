package agibank.tech.test.parser;

import java.util.List;

import agibank.tech.test.model.Sale;
import agibank.tech.test.model.ItemSale;
import agibank.tech.test.util.IdentityTypeEnum;

public class SaleParser {

	private static final String SEPARATOR = "ç";

    public static Sale parse(String line) throws Exception {

        if(!line.startsWith(IdentityTypeEnum.SALE.getId()))
            throw new Exception("Invalid identifier, line must start with: " + IdentityTypeEnum.SALE.getId());

        String[] data = line.split(SEPARATOR);

        if(data.length != 4){
            throw new Exception("Invalid sale information");
        }

        List<ItemSale> itens = SaleItemParser.parseSale(data[2]);

        Sale venda = new Sale(Long.parseLong(data[1]), itens, data[3]);
        venda.updateTotal();

        return venda;
    }
}