package com.restful.gestaodepedidos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.domain.enums.Role;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@WebAppConfiguration
public class UserRepositoryTest {
	
	@Autowired UserRepository repository;
	
	@Test
	@Order(1)
	public void saveTest() {
		
		User user = new User(
				null,
				"Hugo",
				"hugo@gmail.com",
				"123",
				Role.ADMINISTRATOR,
				null, //Pedido
				null); //state
		
		User createUser = repository.save(user);
		
		assertThat(createUser.getId()).isEqualTo(createUser);
	}
	
	@Test
	@Order(2)
	public void update() {
		
		User user = new User(
				1L,
				"Luiz",
				"hugo@gmail.com",
				"123",
				Role.ADMINISTRATOR,
				null, //Pedido
				null); //state
		
		User updateUser = repository.save(user);
		
		assertThat(updateUser.getId()).isEqualTo("Luiz");
	}
	
	@Test
	@Order(3)
	public void getByIdTest() {
		
		Optional<User> result = repository.findById(1L); //Buscar um usuário por meio de uma id .
		User user = result.get(); //Pegar usuário listado por meio de uma id .
		
		assertThat(user.getPassword()).isEqualTo("123");
	}
	
	@Test
	@Order(4)
	public void listTest() {
		
		List<User> users = repository.findAll();
		
		assertThat(users.size()).isEqualTo("1");
	}
	
	@Test
	@Order(5)
	public void loginTest() {
		
		Optional<User> result = repository.login("hugo@gmail.com", "123"); //Buscar um usuário por meio do email e senha
		User loggerUser = result.get(); //Pegar usuário listado por meio de uma id .
		
		assertThat(loggerUser.getId()).isEqualTo(1L);
	}

}
