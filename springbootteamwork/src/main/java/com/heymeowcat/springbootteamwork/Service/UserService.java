package com.heymeowcat.springbootteamwork.Service;

import java.util.ArrayList;
import java.util.List;

import com.heymeowcat.springbootteamwork.Repository.UserRepository;
import com.heymeowcat.springbootteamwork.models.UsersDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserRepository userRespository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

    public List<UsersDao> getAllUsers() {
        return userRespository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<UsersDao> addUsers(List<UsersDao> uList) {
        return userRespository.saveAll(uList);
    }

    public UsersDao addUser(UsersDao user) {
        UsersDao newUser = new UsersDao();
        newUser.setName(user.getName());
        newUser.setTelephone(user.getTelephone());
        newUser.setAge(user.getAge());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRespository.save(newUser);
    }


    public UsersDao updateUser(UsersDao user) {
        UsersDao availableUser = userRespository.findById(user.getId()).orElse(null);
        if (availableUser != null) {
            availableUser.setName(user.getName());
            availableUser.setAge(user.getAge());
            availableUser.setUsername(user.getUsername());
            availableUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            availableUser.setTelephone(user.getTelephone());
        }
        return userRespository.save(availableUser);
    }

    public String deleteUser(int id) {
        UsersDao availableUser = userRespository.findById(id).orElse(null);
        if (availableUser != null) {
            userRespository.delete(availableUser);
            return "User Deleted : " + id;
        }else{
            return "User not Found";
        }
  
    }

    public UsersDao getUserById(int id) {
        return userRespository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     
		UsersDao users = userRespository.findByUsername(username);
		if (users == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(),
				new ArrayList<>());
    }



}
