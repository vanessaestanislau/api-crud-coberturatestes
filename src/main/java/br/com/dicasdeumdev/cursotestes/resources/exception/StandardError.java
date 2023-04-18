package br.com.dicasdeumdev.cursotestes.resources.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String path;
	
	public StandardError(LocalDateTime now, int value, String message) {
		// TODO Auto-generated constructor stub
	}
}
