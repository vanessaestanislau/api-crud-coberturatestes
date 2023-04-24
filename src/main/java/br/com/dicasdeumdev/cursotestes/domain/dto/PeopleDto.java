package br.com.dicasdeumdev.cursotestes.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) /*liberado apenas para escrita*/
	private String password;
}
