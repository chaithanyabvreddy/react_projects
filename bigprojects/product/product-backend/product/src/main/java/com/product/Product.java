package com.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String price;
	private String noOfProducts;
	private String productColor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNoOfProducts() {
		return noOfProducts;
	}
	public void setNoOfProducts(String noOfProducts) {
		this.noOfProducts = noOfProducts;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	
}
