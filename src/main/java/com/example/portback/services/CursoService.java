package com.example.portback.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portback.exceptions.InvalidInfoException;
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

	public List<CursoForm> findAllCursos() {
		return mapCursoToForm(cursoRepo.findAll());
	}

	public List<CursoForm> mapCursoToForm(List<CursoVO> cursosVO) {
		return cursosVO.stream().map(CursoForm::new).collect(Collectors.toList());
	}

	public void updateCurso(CursoForm curso) {
		CursoVO originalCurso = cursoRepo.findById(recoverId(curso.getUrlCertificado())).orElse(null);
		cursoRepo.save(originalCurso.updateCurso(curso));
	}
	
	public void updateCurso(Long id, CursoForm curso) {
		CursoVO originalCurso = cursoRepo.findById(id).orElseThrow(() -> new InvalidInfoException("Nenhum curso encontrado com este Id"));
		cursoRepo.save(originalCurso.updateCurso(curso));
	}
	
	public Long recoverId(String certificado) {
		return cursoRepo.findByUrlCertificado(certificado).map(CursoVO::getId).orElseThrow(() -> new InvalidInfoException("Nenhum curso encontrado com este certificado"));
	}

	public void delete(Long id) {
		cursoRepo.deleteById(id);
	}

}
