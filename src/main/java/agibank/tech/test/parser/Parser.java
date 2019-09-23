package agibank.tech.test.parser;

import java.io.IOException;
import java.util.List;

import agibank.tech.test.model.File;
import agibank.tech.test.service.FileService;
import agibank.tech.test.util.IdentityTypeEnum;

public class Parser implements IFileParser {

    private File file;
    private String path;

    public Parser(String path){
        setPath(path);
    }

    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile(){
        return  file;
    }

    @Override
    public void readFile(List<String> lines) {
        System.out.println("Start read file");
    	file = new File();

        try {
            for (String line : lines) {
                if(line.startsWith(IdentityTypeEnum.SALESMAN.getId())){
                    file.addSalesman(SalesmanParser.parse(line));
                } else if(line.startsWith(IdentityTypeEnum.CUSTOMER.getId())){
                    file.addCustomer(CustomerParser.parse(line));
                } else if(line.startsWith(IdentityTypeEnum.SALE.getId())){
                    file.addSale(SaleParser.parse(line));
                } else{
                    throw new Exception("Invalid data");
                }
            }
            System.out.println("File readed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile(String fileName) {
    	System.out.println("Start write result file");
        try {
            String pathFile = path.concat("\\").concat(fileName.replace(".dat", ".result.dat"));

            FileService service = new  FileService(pathFile);
            service.writeLine(String.format("Quantidade de clientes no arquivo de entrada: %s", file.getCustomersQuantity()));
            service.writeLine(String.format("Quantidade de vendedores no arquivo de entrada: %s", file.getSalesmenQuantity()));
            service.writeLine(String.format("ID da venda mais cara: %s", file.getBiggestSale()));
            service.writeLine(String.format("O pior vendedor: %s", file.getWorstSalesman().getName()));
            service.close();
            System.out.println("Result file finished");
        } catch (IOException e) {
            return;
        }
    }
}
