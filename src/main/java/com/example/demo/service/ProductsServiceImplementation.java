package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Product;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductsServiceImplementation implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public String upsert(Product product) {
        productRepo.save(product);
        return "SUCCESS";
    }

    @Override
    public Product getById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepo.findByNameContainingIgnoreCase(keyword);
    }
    @Override
    public String updateById(Product product) {
        if (productRepo.existsById(product.getId())) {
            productRepo.save(product);
            return "Updated Successfully";
        }
        return "Product Not Found";
    }

    @Override
    public String deleteById(Integer id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Deleted Successfully";
        }
        return "Product Not Found";
    }
}