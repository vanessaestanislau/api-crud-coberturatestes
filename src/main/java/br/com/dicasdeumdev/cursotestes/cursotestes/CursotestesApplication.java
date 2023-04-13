package br.com.dicasdeumdev.cursotestes.cursotestes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dicasdeumdev.domain.User;


@SpringBootApplication
public class CursotestesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursotestesApplication.class, args);
		
		User user = new User(1, "Valdir", "valdir@email.com", "1234");
		
	}

}
