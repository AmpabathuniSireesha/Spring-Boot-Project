package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createProduct(@RequestBody Product product){
		String status = productService.upsert(product);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
@GetMapping("/get/{id}")
public ResponseEntity<Product> getProductById(@PathVariable Integer id){
	Product product = productService.getById(id);
	return new ResponseEntity<>(product,HttpStatus.OK);
}
@GetMapping("/get-all")
public ResponseEntity<List<Product>> getAllProducts(){
	List<Product> products = productService.getAllProducts();
	return new ResponseEntity<>(products,HttpStatus.OK);
}

@GetMapping("/search")
public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
    List<Product> products = productService.searchProducts(keyword);
    return ResponseEntity.ok(products);
}
@PutMapping("/update/{id}")
public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
    product.setId(id); 
    String status = productService.updateById(product);
    if ("Product record updated successfully.".equals(status)) {
        return new ResponseEntity<>("Product updated successfully.", HttpStatus.OK);
    }
    return new ResponseEntity<>("Product not found or update failed.", HttpStatus.NOT_FOUND);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
	String status = productService.deleteById(id);
	return new ResponseEntity<>(status,HttpStatus.OK);
}
}
