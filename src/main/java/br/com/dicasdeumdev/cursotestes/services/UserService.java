package br.com.dicasdeumdev.cursotestes.services;

import java.util.List;

import br.com.dicasdeumdev.cursotestes.domain.People;

public interface UserService {

	People findById(Integer id);
	
	List<People> findAll();
}
