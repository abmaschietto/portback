package com.example.portback.DTO;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ErrorResponseDto {
	
	@Getter
	private String errorMessage;
	@Getter
	private HttpStatus statusHttp;
	@Getter
	private String errorUri;
	@Getter
	private List<FormularioHandlerDto> listaErrosFormulario;
	
	//construtor padrão
	public ErrorResponseDto(String errorMessage, String errorUri) {
		this.errorMessage = errorMessage;
		this.errorUri = errorUri;
		this.statusHttp = HttpStatus.BAD_REQUEST;
	}
	
	//construtor para erros de formulário
	public ErrorResponseDto(String errorMessage, String errorUri, List<FormularioHandlerDto> errosFormulario) {
		this.errorMessage = errorMessage;
		this.errorUri = errorUri;
		this.statusHttp = HttpStatus.BAD_REQUEST;
		this.listaErrosFormulario = errosFormulario;
	}
}
