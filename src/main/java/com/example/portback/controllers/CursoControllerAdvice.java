package com.example.portback.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.portback.DTO.ErrorResponseDto;
import com.example.portback.DTO.FormularioHandlerDto;
import com.example.portback.exceptions.CursoInvalidoException;

@RestControllerAdvice
public class CursoControllerAdvice {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(CursoInvalidoException.class)
	public ResponseEntity<ErrorResponseDto> cursoInvalidoHandler(CursoInvalidoException ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(new ErrorResponseDto(ex.getMessage(), request.getRequestURI()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> invalidFormHnadler(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<FormularioHandlerDto> listErrors = new ArrayList<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			String field = error.getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			FormularioHandlerDto formularioErrorHandlerDto = new FormularioHandlerDto(field, message);
			listErrors.add(formularioErrorHandlerDto);
		});
		return ResponseEntity.badRequest().body(new ErrorResponseDto("Formulário foi preenchido com os seguintes erros:", request.getRequestURI(), listErrors));
	}
}
