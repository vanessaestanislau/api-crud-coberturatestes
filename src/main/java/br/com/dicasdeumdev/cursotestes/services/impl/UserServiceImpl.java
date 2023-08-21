package br.com.dicasdeumdev.cursotestes.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dicasdeumdev.cursotestes.domain.People;
import br.com.dicasdeumdev.cursotestes.domain.dto.PeopleDto;
import br.com.dicasdeumdev.cursotestes.repositories.UserRepository;
import br.com.dicasdeumdev.cursotestes.services.UserService;
import br.com.dicasdeumdev.cursotestes.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.cursotestes.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public People findById(Integer id) {
		Optional<People> obj = repository.findById(id);
				
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public List<People> findAll() {
		return repository.findAll();
	}

	@Override
	public People create(PeopleDto obj) { 
		findByEmail(obj);
		return repository.save(mapper.map(obj, People.class));
	}
		
	@Override
	public People update(PeopleDto obj) {
		findByEmail(obj);
		return repository.save(mapper.map(obj, People.class));		
	}
	
	private void findByEmail(PeopleDto obj) {
		Optional<People> user = repository.findByEmail(obj.getEmail());
		if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
			throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
		}
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}	
	
}
