package com.heymeowcat.springbootteamwork.Service;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.heymeowcat.springbootteamwork.Repository.ProductCategoryRepository;
import com.heymeowcat.springbootteamwork.models.Categories;

@Service
public class CategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	public List<Categories> getAllCategories() {
		return productCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryid"));
	}

	public List<Categories> addCategories(List<Categories> cList) {
		return productCategoryRepository.saveAll(cList);
	}

	public Categories addCategory(Categories category) {
		return productCategoryRepository.save(category);
	}

	public Categories updateCategory(Categories category) {
		Categories availableCategories = productCategoryRepository.findById(category.getCategoryid()).orElse(null);
		if (availableCategories != null) {
			availableCategories.setCategoryName(category.getCategoryName());
			availableCategories.setCategoryDescription(category.getCategoryDescription());
		}
		return productCategoryRepository.save(availableCategories);
	}

	public String deleteCategory(int cid) {
		Categories availableCategory = productCategoryRepository.findById(cid).orElse(null);
		if (availableCategory != null) {
			productCategoryRepository.delete(availableCategory);
			return "Category Deleted : " + cid;
		} else {
			return "Category not Found";
		}

	}

	public Categories getCategoryByid(int pid) {
		return productCategoryRepository.findById(pid).orElse(null);
	}
}
