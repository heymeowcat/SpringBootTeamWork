package com.heymeowcat.springbootteamwork.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.heymeowcat.springbootteamwork.Repository.ProductRepository;
import com.heymeowcat.springbootteamwork.models.Products;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Products> getAllProducts() {
		return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public List<Products> addProducts(List<Products> pList) {
		return productRepository.saveAll(pList);
	}

	public Products addProduct(Products product) {
		return productRepository.save(product);
	}

	public Products updateProduct(Products product) {
		Products availableProduct = productRepository.findById(product.getProductid()).orElse(null);
		if (availableProduct != null) {
			availableProduct.setProductname(product.getProductname());
			availableProduct.setBuyprice(product.getBuyprice());
			availableProduct.setSellprice(product.getSellprice());
			availableProduct.setProductdescription(product.getProductdescription());
			availableProduct.setCategory(product.getCategory());
		}
		return productRepository.save(availableProduct);
	}

	public String deleteProduct(int pid) {
		Products availableProduct = productRepository.findById(pid).orElse(null);
		if (availableProduct != null) {
			productRepository.delete(availableProduct);
			return "Product Deleted : " + pid;
		} else {
			return "Product not Found";
		}

	}

	public Products getProductById(int pid) {
		return productRepository.findById(pid).orElse(null);
	}
}
