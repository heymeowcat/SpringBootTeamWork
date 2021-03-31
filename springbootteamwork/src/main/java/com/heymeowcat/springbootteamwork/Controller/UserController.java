package com.heymeowcat.springbootteamwork.Controller;

import java.util.List;

import com.heymeowcat.springbootteamwork.Service.UserService;
import com.heymeowcat.springbootteamwork.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUsers")
    public List<Users> addUsers(@RequestBody List<Users> uList) {
        return userService.addUsers(uList);
    }

    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users user) {
        return userService.addUser(user);
    }


    @PostMapping("/saveUser")
    public Users saveUser(@RequestBody @RequestParam("name")String name,@RequestBody @RequestParam("age")Integer age,@RequestBody @RequestParam("telephone")Integer telephone,@RequestBody @RequestParam("username")String username,@RequestParam("password")String password) {
        return userService.addUser(new Users(0,name, age, telephone, username, password));
    }
    

    @PutMapping("/updateUser")
    public Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("{id}")
    public Users getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }



}
