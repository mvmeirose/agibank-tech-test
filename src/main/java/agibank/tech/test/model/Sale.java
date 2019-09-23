package agibank.tech.test.model;

import java.math.BigDecimal;
import java.util.List;

public class Sale {

    private Long id;
    private List<ItemSale> itens;
    private Salesman salesman;
    private String salesmanName;
    private BigDecimal totalAmount;

    public Sale(Long id, List<ItemSale> itens, String salesmanName) {
    	setId(id);
    	setItens(itens);
    	setSalesmanName(salesmanName);
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemSale> getItens() {
		return itens;
	}

	public void setItens(List<ItemSale> itens) {
		this.itens = itens;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void updateTotal() {
        this.setTotalAmount(new BigDecimal(0));

        BigDecimal sum = new BigDecimal(0);
        for (ItemSale item: itens) {
            sum = sum.add(item.getPrice().multiply(item.getQuantity()));
        }

        this.setTotalAmount(sum);
    }
}