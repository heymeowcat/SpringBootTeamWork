package com.heymeowcat.springbootteamwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heymeowcat.springbootteamwork.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
    Users findByUsername(String username);
}
