package br.com.dicasdeumdev.cursotestes.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException {
	
	public DataIntegratyViolationException(String message) {
		super(message);
	}	
}
