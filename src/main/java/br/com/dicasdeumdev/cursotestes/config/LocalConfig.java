package br.com.dicasdeumdev.cursotestes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.dicasdeumdev.cursotestes.domain.People;
import br.com.dicasdeumdev.cursotestes.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		People u1 = new People(null, "Valdir", "valdir@email.com", "1234");
		People u2 = new People(null, "Luiz", "luiz@emailcom", "1234");
		
		repository.saveAll(List.of(u1, u2));
		}
}
