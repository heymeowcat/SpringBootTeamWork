package com.heymeowcat.springbootteamwork.Repository;

import com.heymeowcat.springbootteamwork.models.UsersDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UsersDao, Integer>{
    UsersDao findByUsername(String username);
}
