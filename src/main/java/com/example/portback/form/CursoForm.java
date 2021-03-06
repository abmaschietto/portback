package com.example.portback.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.portback.models.CursoVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CursoForm {
	
	@NotEmpty 
	@Length(min = 2, max = 50)
	private String nome;
	
	@NotEmpty 
	@Length(min = 10, max = 200)
	private String urlCertificado;
	
	public CursoForm(CursoVO curso) {
		this.nome = curso.getNome();
		this.urlCertificado = curso.getUrlCertificado();
	}

}
