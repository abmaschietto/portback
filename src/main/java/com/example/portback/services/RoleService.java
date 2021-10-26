package com.example.portback.services;

import java.util.List;

import com.example.portback.models.Role;

public interface RoleService {
	
	Role getRole(String roleName);
	List<Role> getRoles();
	Role saveRole(Role role);
	

}
