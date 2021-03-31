package com.heymeowcat.springbootteamwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heymeowcat.springbootteamwork.models.Categories;

@Repository
public interface ProductCategoryRepository extends JpaRepository<Categories, Integer>  {

}
