package com.example.portback.exceptions;

public class InvalidInfoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidInfoException(String errorMessage) {
		super(errorMessage);
	}
}
