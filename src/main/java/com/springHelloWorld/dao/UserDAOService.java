package com.springHelloWorld.dao;

import com.springHelloWorld.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {

	@Getter
	@Setter
	private static List<User> users = new ArrayList<>();
	//To keep the number of objects (to be used while inserting data)
	static int usersCount;

	static {
		users.add(new User(1, "Nitin", new Date()));
		users.add(new User(2, "Kirti", new Date()));
		usersCount = users.size();
	}

	// Retrieve all users
	public List<User> findAll() {
		return getUsers();
	}
	
	// Retrieve users by Id
	public User findById(int id) {
		//For ArrayIndexOutOfBounds
		if(id >= usersCount){
			return null;
		}

		if(getUsers().get(id) == null) {
			return null;
		}
		return getUsers().get(id);
	}

	//Save a new User
	public User save(User user) {
		if(user.getId() == null){
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	//Delete a user
	public User deleteById(int id){
		Iterator<User> itr = users.iterator();
		User deletedUser=null;
		//boolean idExists = false;
		while(itr.hasNext()){
			User currentUser = itr.next();
			if(id == currentUser.getId()){
				//idExists=true;
				deletedUser = currentUser;
				itr.remove();
			}
		}

		return deletedUser;
	}
}
