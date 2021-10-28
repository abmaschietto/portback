package com.example.portback.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portback.exceptions.InvalidInfoException;
import com.example.portback.models.Role;
import com.example.portback.models.User;import com.example.portback.repositories.RoleRepository;
import com.example.portback.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

	@Autowired
	UserServiceImpl userService;

	@MockBean
	UserRepository userRepository;
	
	@MockBean
	RoleService roleService;
	
	static final String firstRoleName = "ADMIN";
	static final String secondRoleName = "USER";
	
	static final String notToBeFoundUser = "Unknow User";
	static final String validUserName = "Artur";
	static final String anotherValidUserName = "Pepe";
	
	Role role = new Role();
	Role role2 = new Role();
	
	User user = new User();
	User user2 = new User();

	@BeforeEach
	void setUp() throws Exception {
		role.setId(1l);
		role.setName(firstRoleName);
		role.setId(2l);
		role.setName(secondRoleName);
		user.setId(1l);
		user.setName(validUserName);
		user.setRoles(new ArrayList<>(Arrays.asList(role)));
		user2.setId(2l);
		user2.setName(anotherValidUserName);
		user2.setRoles(new ArrayList<>(Arrays.asList(role)));
		Mockito.when(userRepository.findByName(validUserName)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(user, user2)));
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(roleService.getRole(firstRoleName)).thenReturn(role);
	}
	
	@DisplayName(value = "Save User")
	@Test
	void testSaverUser() {
		User userSaved = userRepository.save(user);
		assertAll("Testing User attributes",
				() -> assertEquals(userSaved.getName(), user.getName(), "Should been the same User name"),
				() -> assertEquals(userSaved.getId(), user.getId(), "Should been the same User Id"));
	}

	@DisplayName(value = "Testing Role attribution and verify if RoleService is called")
	@Test
	void testAddRoleToUser() {
		 userService.addRoleToUser(validUserName, firstRoleName);
		 verify(roleService, times(1)).getRole(firstRoleName);
	}

	@DisplayName(value = "Find User by name")
	@Test
	void testGetUser() {
		User user = userService.getUser(validUserName);
		assertAll("Testing the Role fields", 
				() -> assertEquals(validUserName, user.getName(), "Name is incorrect"),
				() -> assertEquals(1l, user.getId(), "Id is incorrect"));
	}
	
	@DisplayName(value = "Testing User not found Exception")
	@Test
	void shouldFailGetRole() {
		InvalidInfoException invalidInfoException = assertThrows(InvalidInfoException.class, () -> userService.getUser(notToBeFoundUser));
		assertEquals("No User found under the name: " + notToBeFoundUser, invalidInfoException.getMessage(), "Exception Message is incorrect");
	}
	
	@DisplayName(value = "Test list of Users")
	@Test
	void testGetUsers() {
		List<User> users = userService.getUsers();
		assertAll("Testing method to return all Users",
				() -> assertEquals(2, users.size(), "List size should have more than 2 users"),
				() -> assertFalse(users.isEmpty(), "List should not be empty"));
	}

}
