package com.product.dao;

import org.springframework.data.repository.CrudRepository;

import com.product.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
