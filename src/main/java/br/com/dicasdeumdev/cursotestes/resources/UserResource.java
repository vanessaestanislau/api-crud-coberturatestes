package br.com.dicasdeumdev.cursotestes.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dicasdeumdev.cursotestes.domain.People;
import br.com.dicasdeumdev.cursotestes.domain.dto.PeopleDto;
import br.com.dicasdeumdev.cursotestes.services.UserService;

@RestController
@RequestMapping(value = "user")
public class UserResource {
	
	@Autowired
	private ModelMapper mapper; /*em formato de bean, tenho aqui uma instância do ModelMapper*/
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PeopleDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), PeopleDto.class));
	}
	
	@GetMapping
	public ResponseEntity<List<PeopleDto>> findAll() {
		//List<People> list = service.findAll();
		//List<PeopleDto> listDto = list.stream().map(x -> mapper.map(x, PeopleDto.class)).collect(Collectors.toList());
		
		//List<PeopleDto> listDto = service.findAll().stream().map(x -> mapper.map(x, PeopleDto.class)).collect(Collectors.toList());
		//return ResponseEntity.ok().body(listDto);
				
		return ResponseEntity.ok()
				.body(service.findAll() //retorna lista de People
						.stream().map(x -> mapper.map(x, PeopleDto.class)).collect(Collectors.toList()));
										//cada obj dessa lista "x" estamos mapeando, transformando num dto e add no toList e retornando pro usuario
	}
}





