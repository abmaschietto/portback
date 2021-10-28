package com.example.portback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portback.exceptions.InvalidInfoException;
import com.example.portback.models.Role;
import com.example.portback.models.User;
import com.example.portback.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleService roleService;

	@Override
	public User saverUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		Role role = roleService.getRole(roleName);
		User user = getUser(userName);
		log.info("Adding role: {} to user {}", role.getName(), user.getName());
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String userName) {
		return userRepo.findByName(userName).orElseThrow(() -> new InvalidInfoException("No User found under the name: " + userName));
	}

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

}
