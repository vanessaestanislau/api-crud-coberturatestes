package br.com.dicasdeumdev.cursotestes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDto {
	
	private Integer id;	
	private String nome;
	private String email;
	
	@JsonIgnore
	private String password;
}
