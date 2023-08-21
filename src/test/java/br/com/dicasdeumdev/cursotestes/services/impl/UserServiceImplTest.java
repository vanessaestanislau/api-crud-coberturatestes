package br.com.dicasdeumdev.cursotestes.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dicasdeumdev.cursotestes.domain.People;
import br.com.dicasdeumdev.cursotestes.domain.dto.PeopleDto;
import br.com.dicasdeumdev.cursotestes.repositories.UserRepository;
import br.com.dicasdeumdev.cursotestes.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.cursotestes.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";

	private static final String SENHA = "123";

	private static final String EMAIL = "val@email.com";

	private static final String NOME = "Valdir";

	private static final Integer ID = 1;

	@InjectMocks //instância real
	private UserServiceImpl userService;
	
	@Mock
	private ModelMapper mapper;
	
	@Mock
	private UserRepository repository;	
	
	@Mock
	private People user;
	
	@Mock
	private PeopleDto userDto;
	
	private Optional<People> optionalUser;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);		
		startUser(); //atribui valores a instancias (evitando nullpointer)
	}
	
	@Test
	void whenFindByIdThenReturnAnPeopleInstance() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		People response = userService.findById(ID);
		
		assertNotNull(response);
		assertEquals(People.class, response.getClass());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			userService.findById(ID);
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundException.class, ex.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
		}
	}
	
	@Test
	void whenFindAllThenReturnListOfPeople() {
		when(repository.findAll()).thenReturn(List.of(user));
		
		List<People> response = userService.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(People.class, response.get(0).getClass());
		
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(EMAIL, response.get(0).getEmail());
		assertEquals(SENHA, response.get(0).getPassword());		
	}
	
	@Test
	void whenCreateThenReturnSuccess() {
		when(repository.save(any())).thenReturn(user);
		
		People response = userService.create(userDto);
		
		assertNotNull(response);
		assertEquals(People.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SENHA, response.getPassword());		
		
	}
	
	@Test
	void whenCreateThenReturnAnDatataIntegrityViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			userService.create(userDto);
		} catch (Exception ex) {
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
			assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
		}		
	}
	
	
	//método para iniciar valores de instância de usuários, evitando o nullpointer	
	private void startUser() { 
		user = new People(ID, NOME, EMAIL, SENHA);
		userDto = new PeopleDto(ID, NOME, EMAIL, SENHA);
		optionalUser = Optional.of(new People(ID, NOME, EMAIL, SENHA));
	}

}
