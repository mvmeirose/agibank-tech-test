package agibank.tech.test.model;

import java.math.BigDecimal;

public class ItemSale {

    private Long id;
    private BigDecimal quantity;
    private BigDecimal price;

    public ItemSale(Long id, BigDecimal quant, BigDecimal price) {
    	setId(id);
    	setQuantity(quant);
    	setPrice(price);
    }

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}

    public BigDecimal getQuantity() {
		return quantity;
	}

    public void setQuantity(BigDecimal quant) {
		this.quantity = quant;
	}

    public BigDecimal getPrice() {
		return price;
	}

    public void setPrice(BigDecimal preco) {
		this.price = preco;
	}
}