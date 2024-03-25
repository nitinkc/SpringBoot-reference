package com.spring.reference.controller;

import com.spring.reference.dto.AdminDTO;
import com.spring.reference.exception.business.UserNotFoundException;
import com.spring.reference.model.User;
import com.spring.reference.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
@Slf4j
@Validated
@AllArgsConstructor
public class F_UserController_ValidationsAndExceptions {

	private UserService userService;

	//Retrieve all users
	@GetMapping(path = "/all")
	public List<User> retrieveAllUsers(){
		return userService.findAll();
	}

	//Retrieve specific users
	@GetMapping(path = "/{id}")
	public User retrieveUserById(@PathVariable UUID id) throws UserNotFoundException {
		return userService.findById(id);
	}

	//Add a new User
	@PostMapping("/add")
	public ResponseEntity<Map<String,Object>> addNewUser(@Valid @RequestBody User user){
		// Check if the user with the same identifier already exists
		/*if (userService.findByphoneOrEmail(user).size() > 0) {
			// Resource already exists, return 409 Conflict
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(Collections.singletonMap("message", "User with the same ID already exists"));
		}*/

		User savedUser = userService.save(user);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(getStringObjectMap(savedUser));
	}

	@PostMapping("/admin/add")
	public ResponseEntity<AdminDTO> createAdmin(
			@Validated(AdminDTO.AdminValidation.class) @RequestBody AdminDTO adminDTO) {

		log.info(adminDTO.toString());
		// Business logic to create an admin
		return ResponseEntity.ok(adminDTO);
	}

	@PutMapping("/add")
	public ResponseEntity<Map<String,Object>> addNewUserWithPut(@Valid @RequestBody User user){

		// Check if the user already exists
		if (null == userService.findById(user.getId())) {
			// Resource does not exist, return 404 Not Found
			return ResponseEntity.notFound().build();
		}
		User savedUser = userService.updateUser(user);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(getStringObjectMap(savedUser));
	}

	private static Map<String, Object> getStringObjectMap(User savedUser) {
		//Send the URI of the saved Object
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("location", location);
		returnMap.put("savedUser", savedUser);
		return returnMap;
	}


	//Delete a User
	/*@DeleteMapping(path = "/user/{id}")
	public User deleteUserById(@PathVariable int id) throws UserNotFoundException {
		User user = userRepository.deleteById(id);

		//If user is not found
		if(user == null){
			throw new UserNotFoundException("User with id " + id +" is not found");
		}
		return user;
	}*/
}
