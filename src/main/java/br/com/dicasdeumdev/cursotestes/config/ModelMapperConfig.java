package br.com.dicasdeumdev.cursotestes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean /*ao invés de criar uma instancia, vou fazer posteriormente uma injeção dessa instância e o spring irá gerenciar*/
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
