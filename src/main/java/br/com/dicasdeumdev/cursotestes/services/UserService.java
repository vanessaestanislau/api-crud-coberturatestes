package br.com.dicasdeumdev.cursotestes.services;

import br.com.dicasdeumdev.cursotestes.domain.People;

public interface UserService {

	People findById(Integer id);
	
}
