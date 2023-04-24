package br.com.dicasdeumdev.cursotestes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dicasdeumdev.cursotestes.domain.dto.PeopleDto;
import br.com.dicasdeumdev.cursotestes.services.UserService;

@RestController
@RequestMapping(value = "user")
public class UserResource {
	
	private static final String ID = "/{id}";

	@Autowired
	private ModelMapper mapper; /*em formato de bean, tenho aqui uma instância do ModelMapper*/
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = ID)
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
	
	@PostMapping
	public ResponseEntity<PeopleDto> create(@RequestBody PeopleDto obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(ID).buildAndExpand(service.create(obj).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = ID)
	public ResponseEntity<PeopleDto> update(@PathVariable Integer id, @RequestBody PeopleDto obj) {
		obj.setId(id);
		return ResponseEntity.ok().body(mapper.map(service.update(obj), PeopleDto.class));
	}
	
	@DeleteMapping(value = ID)
	public ResponseEntity<PeopleDto> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}

}





