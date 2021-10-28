package com.example.portback.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.example.portback.repositories.RoleRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoleServiceImplTest {

	@Autowired
	RoleServiceImpl roleService;

	@MockBean
	RoleRepository roleRepository;

	Role role = new Role();
	Role role2 = new Role();
	
	static final String firstRoleName = "ADMIN";
	static final String secondRoleName = "USER";
	static final String notToBeFoundRoleName = "OWNER";
	
	
	@BeforeEach
	void setUp() throws Exception {
		role.setId(1l);
		role.setName(firstRoleName);
		role2.setId(2l);
		role2.setName(secondRoleName);
		Mockito.when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(role));
		Mockito.when(roleRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(role, role2)));
		Mockito.when(roleRepository.save(role)).thenReturn(role);

	}

	@DisplayName(value = "Return Role by Name")
	@Test
	void testGetRole() {
		Role roleReturned = roleService.getRole(firstRoleName);
		assertAll("Testing the Role fields", 
				() -> assertEquals(firstRoleName, roleReturned.getName(), "Name is incorrect"),
				() -> assertEquals(1l, roleReturned.getId(), "Id is incorrect"));
	}

	@DisplayName(value = "Return all Roles")
	@Test
	void testGetRoles() {
		List<Role> roles = roleService.getRoles();
		assertAll("Testing method to return all Roles",
				() -> assertEquals(2, roles.size(), "List size should have more than 2 objects"),
				() -> assertFalse(roles.isEmpty(), "List should not be empty"));
	}

	@DisplayName(value = "Save Role")
	@Test
	void testSaveRole() {
		Role roleSaved = roleRepository.save(role);
		assertAll("Testing Role attributes",
				() -> assertEquals(roleSaved.getName(), role.getName(), "Should been the same Role name"),
				() -> assertEquals(roleSaved.getId(), role.getId(), "Should been the same Role Id"));
	}

	@DisplayName(value = "Testing Role exception")
	@Test
	void shouldFailGetRole() {
		InvalidInfoException invalidInfoException = assertThrows(InvalidInfoException.class, () -> roleService.getRole(notToBeFoundRoleName));
		assertEquals("No Role found under the name: " + notToBeFoundRoleName, invalidInfoException.getMessage(), "Exception Message is incorrect");
	}

}
