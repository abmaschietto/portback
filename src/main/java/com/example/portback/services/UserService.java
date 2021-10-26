package com.example.portback.services;

import java.util.List;

import com.example.portback.models.User;

public interface UserService {
	
	User saverUser(User user);
	void addRoleToUser(String userName, String roleName);
	User getUser(String username);
	List<User> getUsers();

}
