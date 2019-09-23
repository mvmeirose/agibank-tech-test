package agibank.tech.test.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class File {

    private List<Salesman> salesmen;
    private List<Customer> customers;
    private List<Sale> sales;

    public File() {
    	salesmen = new ArrayList<>();
    	customers = new ArrayList<>();
    	sales = new ArrayList<>();
    }

    public List<Salesman> getSalesmen() {
		return salesmen;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void addSalesman(Salesman newSalesman) {
        for (Salesman salesmanRegistered : salesmen) {
            if (salesmanRegistered.getCpf().equals(newSalesman.getCpf()))
                return;
        }

        salesmen.add(newSalesman);

        Sale sale = sales
                .stream()
                .filter(v -> v.getSalesman() == null 
                				&& v.getSalesmanName().equals(newSalesman.getName()))
                .findFirst()
                .orElse(null);

        if (sale != null) {
            sale.setSalesman(newSalesman);
            sale.updateTotal();
            newSalesman.increaseTotalSale(sale.getTotalAmount());
        }
    }

    public void addCustomer(Customer newCustomer) {

        for (Customer customerRegistred: customers) {
            if (customerRegistred.getCnpj().equals(newCustomer.getCnpj()))
                return;
        }

        customers.add(newCustomer);
    }

    public void addSale(Sale newSale) throws Exception {
        if (newSale.getSalesman() != null) {
        	newSale.updateTotal();
        	newSale.getSalesman().increaseTotalSale(newSale.getTotalAmount());
        	newSale.setSalesmanName(newSale.getSalesman().getName());

        } else if (newSale.getSalesmanName() != null && !newSale.getSalesmanName().isEmpty()) {

            Salesman newSalesman = salesmen
                    .stream()
                    .filter(s -> s.getName().equals(newSale.getSalesmanName()))
                    .findFirst()
                    .orElse(null);

            if (newSalesman != null) {
                newSale.setSalesman(newSalesman);
                newSale.updateTotal();
                newSalesman.increaseTotalSale(newSale.getTotalAmount());
            }
        } else {
            throw new Exception("Salesman information not found");
        }

        newSale.updateTotal();
        sales.add(newSale);
    }

    public int getCustomersQuantity() {
        return getCustomers().size();
    }

    public int getSalesmenQuantity() {
        return getSalesmen().size();
    }

    public Long getBiggestSale() {
        Long saleId = getSales()
                .stream()
                .sorted(Comparator.comparing(Sale::getTotalAmount).reversed())
                .map(Sale::getId)
                .findFirst()
                .orElse(null);

        return saleId;
    }

    public Salesman getWorstSalesman(){
        Salesman salesman = getSalesmen()
                .stream()
                .sorted(Comparator.comparing(Salesman::getTotalSale))
                .findFirst()
                .orElse(null);

        return salesman;

    }
}
