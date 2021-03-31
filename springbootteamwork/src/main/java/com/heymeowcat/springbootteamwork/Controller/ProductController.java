package com.heymeowcat.springbootteamwork.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heymeowcat.springbootteamwork.Service.ProductService;
import com.heymeowcat.springbootteamwork.models.Products;

@RestController
@RequestMapping(path = "/Products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getAllProducts")
	public List<Products> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/addProducts")
	public List<Products> addProducts(@RequestBody List<Products> pList) {
		return productService.addProducts(pList);
	}

	@PostMapping("/addProduct")
	public Products addProduct(@RequestBody Products product) {
		return productService.addProduct(product);
	}

	@PutMapping("/updateProduct")
	public Products updateProduct(@RequestBody Products product) {
		return productService.updateProduct(product);
	}

	@DeleteMapping("{id}")
	public String deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}

	@GetMapping("{id}")
	public Products getProduct(@PathVariable int id) {
		return productService.getProductById(id);
	}

}
