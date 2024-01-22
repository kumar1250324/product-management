package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		
		return productRepository.findById(id).orElse(null);
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public void updateProduct(Long id, Product updatedProduct) {
		Product existingProduct = productRepository.findById(id).orElse(null);

		if (existingProduct != null) {
			
			existingProduct.setName(updatedProduct.getName());
			existingProduct.setDescription(updatedProduct.getDescription());
			existingProduct.setPrice(updatedProduct.getPrice());

			productRepository.save(existingProduct);
		}
	}
}
