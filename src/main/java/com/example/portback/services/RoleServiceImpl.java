package com.example.portback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portback.exceptions.InvalidInfoException;
import com.example.portback.models.Role;
import com.example.portback.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Role getRole(String roleName) {
		return roleRepo.findByName(roleName).orElseThrow(() -> new InvalidInfoException("No Role found under the name: " + roleName));
	}

	@Override
	public List<Role> getRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

}
