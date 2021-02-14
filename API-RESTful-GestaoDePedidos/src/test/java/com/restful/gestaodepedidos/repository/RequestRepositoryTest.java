package com.restful.gestaodepedidos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.domain.enums.RequestState;


@SpringBootTest
public class RequestRepositoryTest {
	
	@Autowired
	private RequestRepository repository;
	
	@Test
	public void saveTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(
				null,
				"Notebook Dell",
				"Novo Notebook",
				new Date(),
				RequestState.OPEN,
				owner,
				null);
		
		Request createRequest = repository.save(request);
		assertThat(createRequest.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(
				null,
				"Notebook Dell",
				"Novo Notebook 16Ram 500HD i5",
				new Date(), //O atributo data foi defino como na Classe Request como 'updatable = false'
				RequestState.IN_PROGRESS,
				owner,
				null);
		
		Request updateRequest = repository.save(request);
		assertThat(updateRequest.getDescription()).isEqualTo("Novo Notebook 16Ram 500HD i5");
	}
	
	@Test
	public void getByIdTest() {
		Optional<Request> result = repository.findById(1L);
		
		assertThat(result.get().getSubject()).isEqualTo("Notebook Dell");
	}

	@Test
	public void listTest() {
		List<Request> requests = repository.findAllByOwnerId(1L);
		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void listByOwnerIdTest() {
		List<Request> requests = repository.findAll();
		
		assertThat(requests.size()).isEqualTo(1);
		
	}
	
	@Test
	public void updateStatusTest() {
		int affectedRows = repository.updateStatus(1L, RequestState.OPEN); //id do 
		
		assertThat(affectedRows).isEqualTo(1);
	}

}
