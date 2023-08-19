package br.com.dicasdeumdev.cursotestes.services;


import java.util.List;

import br.com.dicasdeumdev.cursotestes.domain.People;
import br.com.dicasdeumdev.cursotestes.domain.dto.PeopleDto;

public interface UserService {

	People findById(Integer id);
	
	List<People> findAll();
	
	People create(PeopleDto obj);
	
	People update(PeopleDto obj);
	
	void delete(Integer id);
}
