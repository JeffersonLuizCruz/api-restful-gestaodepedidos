package com.restful.gestaodepedidos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.domain.enums.Role;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired UserRepository repository;
	
	/*O Método saveTest() é o método principal. Todos os outros métodos precisam dele
	 * para serem execultado os testes.
	 * */
	
	@Test
	public void saveTest() {
		
		User user = new User(
				null,
				"Hugo",
				"jefferson@gmail.com",
				"12345678",
				Role.SIMPLE,
				null, //Pedido
				null); //state
		
		User createUser = repository.save(user);
		
		assertThat(createUser.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		
		User user = new User(
				9L,
				"Jefferson",
				"jefferson@gmail.com",
				"12345678",
				Role.SIMPLE,
				null, //Pedido
				null); //state
		
		User updateUser = repository.save(user);
		
		assertThat(updateUser.getEmail()).isEqualTo("jefferson@gmail.com");
	}
	
	@Test
	public void getByIdTest() {
		
		Optional<User> result = repository.findById(1L); //Buscar um usuário por meio de uma id .
		User user = result.get(); //Método get() converte 'User user' para um Optional 
		
		assertThat(user.getPassword()).isEqualTo("2147483647");
	}
	
	@Test
	public void listTest() {
		
		List<User> users = repository.findAll();
		
		assertThat(users.size()).isEqualTo(1L);
	}
	
	@Test
	public void loginTest() {
		
		Optional<User> result = repository.login("hugo@gmail.com", "2147483647"); 
		//Buscar um usuário por meio do email e senha
		User loggerUser = result.get(); //Pegar usuário listado por meio de uma id .
		
		assertThat(loggerUser.getId()).isEqualTo(1L);
	}


}
