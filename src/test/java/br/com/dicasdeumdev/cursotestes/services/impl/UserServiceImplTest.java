package br.com.dicasdeumdev.cursotestes.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
import br.com.dicasdeumdev.cursotestes.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {

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
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado!"));
		
		try {
			userService.findById(ID);
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundException.class, ex.getClass());
			assertEquals("Objeto não encontrado!", ex.getMessage());
		}
	}
	
	//método para iniciar valores de instância de usuários, evitando o nullpointer	
	private void startUser() { 
		user = new People(ID, NOME, EMAIL, SENHA);
		userDto = new PeopleDto(ID, NOME, EMAIL, SENHA);
		optionalUser = Optional.of(new People(ID, NOME, EMAIL, SENHA));
	}

}
