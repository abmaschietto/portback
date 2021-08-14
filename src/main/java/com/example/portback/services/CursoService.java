package com.example.portback.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.portback.models.CursoVO;
import com.example.portback.repositories.CursoRepository;

@Component
public class CursoService {
	
	@Autowired
	private CursoRepository cpRepo;
	
	@PostConstruct
	public void teste() {
		CursoVO c = new CursoVO("aaaaa", "www.aaaa.com");
		cpRepo.save(c);
	}

}
