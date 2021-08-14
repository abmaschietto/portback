package com.example.portback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portback.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cpRepo;
	
	

}
