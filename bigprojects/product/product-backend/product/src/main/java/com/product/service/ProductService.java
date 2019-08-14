package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.Product;
import com.product.ProductForm;
import com.product.dao.ProductRepository;

@Service
public class ProductService {

	@Autowired
	public ProductRepository productRepository;

	public ProductForm createProduct(ProductForm productForm) {
		Product product = new Product();
		BeanUtils.copyProperties(productForm, product);
		Product savedProduct = productRepository.save(product);
		productForm.setId(savedProduct.getId());
		return productForm;
	}
	
	public boolean delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<ProductForm> getAllProduct() {
		Iterable<Product> productList =	productRepository.findAll();
		List<ProductForm> productFormList =  new ArrayList<ProductForm>();
		for(Product temp: productList) {
			ProductForm productForm = new ProductForm();
			BeanUtils.copyProperties(temp, productForm);
			productFormList.add(productForm);
		}
		return productFormList;
	}
}
