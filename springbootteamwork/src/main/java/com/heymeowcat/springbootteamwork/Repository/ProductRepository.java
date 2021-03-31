package com.heymeowcat.springbootteamwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heymeowcat.springbootteamwork.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>{

}
