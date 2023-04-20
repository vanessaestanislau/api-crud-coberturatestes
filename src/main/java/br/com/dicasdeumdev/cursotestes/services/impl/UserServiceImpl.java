package br.com.dicasdeumdev.cursotestes.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dicasdeumdev.cursotestes.domain.People;
import br.com.dicasdeumdev.cursotestes.repositories.UserRepository;
import br.com.dicasdeumdev.cursotestes.resources.exception.ObjectNotFoundException;
import br.com.dicasdeumdev.cursotestes.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public People findById(Integer id) {
		Optional<People> obj = repository.findById(id);
		//if (obj.isPresent()) System.out.println("Usuário cadastrado = " + obj.get().getNome());		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
		
	
		
		
		
	}
	
	

}
