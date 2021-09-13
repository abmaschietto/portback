package com.example.portback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portback.form.CursoForm;
import com.example.portback.models.CursoVO;
import com.example.portback.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepo;
	
	
	public void saveCurso(CursoForm curso) {
		cursoRepo.save(new CursoVO(curso.getNome(), curso.getUrlCertificado()));
	}

	public List<CursoVO> findAllCursos() {
		List<CursoVO> findAll = cursoRepo.findAll();
		return findAll;
	}
	
}
