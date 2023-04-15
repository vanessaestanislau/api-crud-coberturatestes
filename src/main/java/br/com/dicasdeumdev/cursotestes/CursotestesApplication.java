package br.com.dicasdeumdev.cursotestes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dicasdeumdev.cursotestes.domain.People;

@SpringBootApplication
public class CursotestesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursotestesApplication.class, args);

		People user = new People(1, "Valdir", "valdir@email.com", "1234");

		
	}

}
