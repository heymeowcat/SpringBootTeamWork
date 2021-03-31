package com.heymeowcat.springbootteamwork.Service;

import java.util.List;

import com.heymeowcat.springbootteamwork.Repository.UserRepository;
import com.heymeowcat.springbootteamwork.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRespository;

    public List<Users> getAllUsers() {
        return userRespository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Users> addUsers(List<Users> uList) {
        return userRespository.saveAll(uList);
    }

    public Users addUser(Users user) {
        return userRespository.save(user);
    }

    public Users updateUser(Users user) {
        Users availableUser = userRespository.findById(user.getId()).orElse(null);
        if (availableUser != null) {
            availableUser.setName(user.getName());
            availableUser.setAge(user.getAge());
            availableUser.setUsername(user.getUsername());
            availableUser.setPassword(user.getPassword());
            availableUser.setTelephone(user.getTelephone());
        }
        return userRespository.save(availableUser);
    }

    public String deleteUser(int id) {
        Users availableUser = userRespository.findById(id).orElse(null);
        if (availableUser != null) {
            userRespository.delete(availableUser);
            return "User Deleted : " + id;
        }else{
            return "User not Found";
        }
  
    }

    public Users getUserById(int id) {
        return userRespository.findById(id).orElse(null);
    }



}
