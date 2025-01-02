package com.example.demo.service;

import java.util.List;

import com.example.demo.Product;

public interface ProductService {
	String upsert(Product product);
	Product getById(Integer id);
	List<Product> getAllProducts();
	String deleteById(Integer id);
	String updateById(Product product);
	List<Product> searchProducts(String keyword);

}
