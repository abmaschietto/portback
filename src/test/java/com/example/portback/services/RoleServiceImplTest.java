package com.example.portback.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

import com.example.portback.models.Role;
import com.example.portback.repositories.RoleRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoleServiceImplTest {
	
	@Autowired
	RoleServiceImpl roleService;
	
	@MockBean
	RoleRepository roleRepository;

	@BeforeEach
	void setUp() throws Exception {
		Role role = new Role();
		role.setId(1l);
		role.setName("ADMIN");
		Role role2 = new Role();
		role2.setId(1l);
		role2.setName("USER");
		Mockito.when(roleRepository.findByName(role.getName()))
			.thenReturn(Optional.of(role));
	}

	@DisplayName(value = "Return Role by Name")
	@Test
	void testGetRole() {
		Role roleReturned = roleService.getRole("ADMIN");
		assertAll("Testando campos de Role retornada",
			() -> assertEquals("ADMIN", roleReturned.getName(), "Os nome da role está incorreto"),
			() -> assertEquals(1l, roleReturned.getId(), "O id da role está incorreto")
			);
	}

	@Test
	void testGetRoles() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveRole() {
		fail("Not yet implemented");
	}

}
