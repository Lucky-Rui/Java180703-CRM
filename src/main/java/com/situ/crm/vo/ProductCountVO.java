package com.situ.crm.vo;

public class ProductCountVO {
	private String name;

	private Double stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductCountVO [name=" + name + ", stock=" + stock + "]";
	}
}
