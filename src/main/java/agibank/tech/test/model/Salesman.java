package agibank.tech.test.model;

import java.math.BigDecimal;

public class Salesman {

    private String cpf;
    private String name;
    private BigDecimal salary;
    private BigDecimal totalSale;

    public Salesman(String cpf, String name, BigDecimal salary) {
    	setCpf(cpf);
    	setName(name);
    	setSalary(salary);
    	this.totalSale = new BigDecimal(0);
    }

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getTotalSale() {
        if(this.totalSale == null)
        	this.totalSale = new BigDecimal(0);

        return this.totalSale;
    }

    public void setTotalSale(BigDecimal value) {
    	this.totalSale = value;
    }

    public void increaseTotalSale(BigDecimal value) {
        if(this.totalSale == null)
        	this.totalSale = new BigDecimal(0);

        this.totalSale = this.totalSale.add(value);
    }
}