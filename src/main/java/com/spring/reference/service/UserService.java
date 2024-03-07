package com.spring.reference.service;

import com.spring.reference.dao.repository.UserRepository;
import com.spring.reference.exception.business.*;
import com.spring.reference.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ExceptionTypeFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);

    return user.orElseThrow(
         //() -> new UserNotFoundException("User with id " + id + " is not found"));
         () -> new BadInputException("User with id " + id + " is not found"));
        // () -> new StudentNotFoundException("User with id " + id + " is not found"));
    }

    public List<User> findByNameAndDateOfBirth(User user) {
        List<User> foundUsers = userRepository.findByNameAndDob(user.getName(), user.getDob());

        return foundUsers;
    }

    public List<User> findByphoneOrEmail(User user) {

        List<User> foundUsers = userRepository.findByPhoneOrEmail(user.getEmail());

        return foundUsers;
    }

    public User save(User user) {
        user.setId(UUID.randomUUID());
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    // Update a user
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}