package com.spring.reference.dao.repository;

import com.spring.reference.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	// Retrieve all users
	public List<User> findAll();
	
	// Retrieve users by Id
	public Optional<User> findById(UUID id);

	//Save a new User
	public User save(User user);

	// Custom query to search users by name and date of birth (DOB)
	@Query("SELECT u FROM User u WHERE u.name = :name AND u.dob = :dob")
	List<User> findByNameAndDob(String name, Date dob);

	// Custom query to search users by phone number or email
	@Query("SELECT u FROM User u WHERE u.phone = :phoneOrEmail OR u.email = :phoneOrEmail")
	List<User> findByPhoneOrEmail(String phoneOrEmail);



	//Delete a user
	//public User deleteById(int id);
}
