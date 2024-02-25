package com.springHelloWorld.controller;

import com.springHelloWorld.model.User;
import com.springHelloWorld.dao.UserDAOService;
import com.springHelloWorld.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/hardCodedData")
public class UserController {

	@Autowired
	private UserDAOService userDAOService;

	//Retrieve all users
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return userDAOService.findAll();
	}

	//Retrieve specific users
	@GetMapping(path = "/user/{id}")
	public User retrieveUserById(@PathVariable int id) throws UserNotFoundException {
		User user = userDAOService.findById(id);

		//If user is not found
		if(user == null){
			throw new UserNotFoundException("User with id " + id +" is not found");
		}
		return user;
	}

	//Add a new User
	@PostMapping("/users")
	public ResponseEntity<Object> addNewUser(@Valid  @RequestBody User user){
		User savedUser = userDAOService.save(user);

		//Send the URI of the saved Object
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	//Delete a User
	@DeleteMapping(path = "/user/{id}")
	public User deleteUserById(@PathVariable int id) throws UserNotFoundException {
		User user = userDAOService.deleteById(id);

		//If user is not found
		if(user == null){
			throw new UserNotFoundException("User with id " + id +" is not found");
		}
		return user;
	}
}
