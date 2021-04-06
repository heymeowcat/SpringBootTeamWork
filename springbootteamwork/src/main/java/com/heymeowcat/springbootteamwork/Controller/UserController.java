package com.heymeowcat.springbootteamwork.Controller;

import java.util.List;
import java.util.Objects;

import com.heymeowcat.springbootteamwork.Config.JwtTokenUtil;
import com.heymeowcat.springbootteamwork.Service.UserService;
import com.heymeowcat.springbootteamwork.models.JwtRequest;
import com.heymeowcat.springbootteamwork.models.JwtResponse;
import com.heymeowcat.springbootteamwork.models.UsersDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;



	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}



    @GetMapping("/getAllUsers")
    public List<UsersDao> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUsers")
    public List<UsersDao> addUsers(@RequestBody List<UsersDao> uList) {
        return userService.addUsers(uList);
    }

    @PostMapping("/addUser")
    public UsersDao addUser(@RequestBody UsersDao user) {
        return userService.addUser(user);
    }


    @PostMapping("/saveUser")
    public UsersDao saveUser(@RequestBody @RequestParam("name")String name,@RequestBody @RequestParam("age")Integer age,@RequestBody @RequestParam("telephone")Integer telephone,@RequestBody @RequestParam("username")String username,@RequestParam("password")String password) {
        return userService.addUser(new UsersDao(0,name, age, telephone, username, password));
    }
    

    @PutMapping("/updateUser")
    public UsersDao updateUser(@RequestBody UsersDao user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("{id}")
    public UsersDao getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }



}
