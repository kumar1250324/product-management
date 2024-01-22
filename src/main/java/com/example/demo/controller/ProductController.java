package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	public static final String PRODUCT_LIST_VIEW = "productList";
	public static final String PRODUCT_DETAILS_VIEW = "productDetails";
	public static final String PRODUCT_FORM_VIEW = "productForm";
	public static final String PRODUCT_EDIT_VIEW = "productEdit";
	public static final String REDIRECT_PRODUCTS = "redirect:/products";

	// Retrieve all products from the service
	@GetMapping
	public String getAllProducts(Model model) {
		try {
			List<Product> products = productService.getAllProducts();
			model.addAttribute("products", products);
			return PRODUCT_LIST_VIEW;
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving products", e);
		}
	}

	// Retrieve product by ID from the service
	@GetMapping("/{id}")
	public String getProductById(@PathVariable Long id, Model model) {
		try {
			Product product = productService.getProductById(id);
			model.addAttribute("product", product);
			return PRODUCT_DETAILS_VIEW;
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving product by ID: " + id, e);
		}
	}

	// Add an empty product object to the model for rendering in the form
	@GetMapping("/new")
	public String showProductForm(Model model) {
		model.addAttribute("product", new Product());
		return PRODUCT_FORM_VIEW;
	}

	// Save the product using the service
	@PostMapping("/new")
	public String saveProduct(@ModelAttribute("product") Product product) {
		try {
			productService.saveProduct(product);
			return REDIRECT_PRODUCTS;
		} catch (Exception e) {

			throw new RuntimeException("Error while saving product", e);
		}
	}

	// Retrieve the product by ID from the service
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable String id, Model model) {
		try {

			Long productId = Long.parseLong(id);
			Product product = productService.getProductById(productId);
			model.addAttribute("product", product);
			return PRODUCT_EDIT_VIEW;

		} catch (Exception e) {

			throw new RuntimeException("Error while retrieving product for editing", e);
		}
	}

	// Delete the product by ID using the service
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		try {

			productService.deleteProduct(id);
			return REDIRECT_PRODUCTS;
		} catch (Exception e) {

			throw new RuntimeException("Error while deleting product", e);
		}
	}

	// Update the product using the service
	@PostMapping("/edit/{id}")
	public String handleEditSubmit(@PathVariable Long id, @ModelAttribute("product") Product updatedProduct) {
		try {

			productService.updateProduct(id, updatedProduct);

			return REDIRECT_PRODUCTS;
		} catch (Exception e) {
			throw new RuntimeException("Error while updating product", e);
		}
	}
}
