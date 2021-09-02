package com.example.portback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portback.models.CursoVO;
import com.example.portback.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepo;
	
	
	public void saveCurso(CursoVO curso) {
		cursoRepo.save(curso);
	}


	public List<CursoVO> findAllCursos() {
		List<CursoVO> findAll = cursoRepo.findAll();
		return findAll;
	}
	
	
}
