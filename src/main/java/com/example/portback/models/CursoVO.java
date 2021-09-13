package com.example.portback.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "CURSOS")
public class CursoVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CURSO_ID")
	private Long id;
	
	@Column(name = "NOME_CURSO")
	@NonNull
	private String nome;
	
	@Column(name = "URL_CERTIFICADO_CURSO")
	@NonNull
	private String urlCertificado;
	
}
