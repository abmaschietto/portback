package com.example.portback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.portback.models.CursoVO;
import com.example.portback.services.CursoService;

@RestController
@RequestMapping("v1/cursos")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping(path = "/listar")
	public ResponseEntity<List<CursoVO>> buscarCursos(){
		List<CursoVO> findAllCursos = cursoService.findAllCursos();
		return ResponseEntity.ok(findAllCursos);
	}
	
	@PostMapping(path = "/inserir")
	public ResponseEntity<String> inserirCurso(@RequestBody CursoVO curso){
		cursoService.saveCurso(curso);
		return ResponseEntity.ok("salvo");
	}

}
