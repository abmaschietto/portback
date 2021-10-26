package com.example.portback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portback.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByName(String name);

}
