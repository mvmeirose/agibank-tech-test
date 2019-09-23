package agibank.tech.test.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import agibank.tech.test.model.File;

public class Parser implements IFileParser {

    private File file;
    private String path;
    
    private static final String SEPARATOR = "ç";
    private static final String SALESMAN = "001";
    private static final String CUSTOMER = "002";
    private static final String SALE = "003";

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
            	final String[] data = line.split(SEPARATOR);

                switch (data[0]) {
	                case SALESMAN:
	                	file.addSalesman(SalesmanParser.parse(data));
	                    break;
	                case CUSTOMER:
	                	file.addCustomer(CustomerParser.parse(data));
	                    break;
	                case SALE:
	                	file.addSale(SaleParser.parse(data));
	                    break;
                }
            }
            System.out.println("File readed");
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    @Override
    public void writeFile(String fileName) {
    	System.out.println("Start write result file");
        try {
        	Path pathFile = Paths.get(path.concat("\\").concat(fileName.replace(".dat", "-result.dat")));
        	 
        	try (BufferedWriter writer = Files.newBufferedWriter(pathFile)) {
        	    writer.write(String.format("Quantidade de clientes no arquivo de entrada: %s", file.getCustomersQuantity()));
        	    writer.newLine();
        	    writer.write(String.format("Quantidade de vendedores no arquivo de entrada: %s", file.getSalesmenQuantity()));
        	    writer.newLine();
        	    writer.write(String.format("ID da venda mais cara: %s", file.getBiggestSale()));
        	    writer.newLine();
        	    writer.write(String.format("O pior vendedor: %s", file.getWorstSalesman().getName()));
        	}
        	
            System.out.println("Result file finished");
        } catch (IOException e) {
            System.out.println("Error writing the result file");
        }
    }
}
