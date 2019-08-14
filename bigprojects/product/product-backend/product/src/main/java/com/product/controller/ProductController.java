package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.ProductForm;
import com.product.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	public ProductService productService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public ProductForm createProduct(@RequestBody ProductForm productForm) {
	ProductForm savedProduct =	productService.createProduct(productForm);
		return savedProduct;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public boolean delete(@RequestParam(name = "id") Long id) {
	boolean isdeleted =	productService.delete(id);
		return isdeleted;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	public List<ProductForm> getAllProduct() {
		List<ProductForm> savedProductList = productService.getAllProduct();
		return savedProductList;
	}
}
