package com.example.portback.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.portback.exceptions.InvalidInfoException;
import com.example.portback.form.CursoForm;
import com.example.portback.services.CursoService;

@RestController
@RequestMapping("v1/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@GetMapping(path = "/listar")
	public ResponseEntity<List<CursoForm>> findAllCursos() {
		List<CursoForm> findAllCursos = cursoService.findAllCursos();
		return ResponseEntity.ok(findAllCursos);
	}

	@PostMapping(path = "/inserir")
	public ResponseEntity<String> insertCurso(@RequestBody @Valid CursoForm curso) throws InvalidInfoException {
		cursoService.saveCurso(curso);
		return ResponseEntity.ok("Curso salvo");
	}

	@GetMapping(path = "/recoverId")
	public ResponseEntity<Long> recoverId(@RequestParam String certificado) {
		return ResponseEntity.ok(cursoService.recoverId(certificado));
	}

	@PutMapping(path = "/atualizarByCertificado")
	public ResponseEntity<String> updateCurso(@RequestBody CursoForm curso ) {
		cursoService.updateCurso(curso);
		return ResponseEntity.ok("Curso atualizado pelo certificado");
	}

	@PutMapping(path = "/atualizarById/{id}")
	public ResponseEntity<String> updateCurso(@PathVariable Long id, @RequestBody CursoForm curso) {
		cursoService.updateCurso(id, curso);
		return ResponseEntity.ok("Curso atualizado pelo Id");
	}

	@DeleteMapping(path = "/deleteCurso/{id}")
	public ResponseEntity<String> deleteCurso(@PathVariable Long id) {
		cursoService.delete(id);
		return ResponseEntity.ok("Curso deletado");
	}

}
