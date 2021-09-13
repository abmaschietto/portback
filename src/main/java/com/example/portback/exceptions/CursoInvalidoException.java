package com.example.portback.exceptions;

public class CursoInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CursoInvalidoException(String errorMessage) {
		super(errorMessage);
	}
}
