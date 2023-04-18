package br.com.dicasdeumdev.cursotestes.resources.exception;

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String message) {
		super(message);
	}
}
