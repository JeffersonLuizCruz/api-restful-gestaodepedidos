package com.restful.gestaodepedidos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.restful.gestaodepedidos.entities.Request;
import com.restful.gestaodepedidos.entities.RequestStage;
import com.restful.gestaodepedidos.entities.User;
import com.restful.gestaodepedidos.enums.RequestState;

@SpringBootTest
public class RequestStageTest {
	
	@Autowired
	private RequestStageRepository repository;
	
	@Test
	public void saveTest() {
		
		Request request = new Request();
		request.setId(1L);
		
		User owner = new User();
		owner.setId(1L);
		
		RequestStage requestStage = new RequestStage(
				null,
				"Notebook HP Comprado Loja Ponto Frio",
				Instant.now(),//O atributo data foi defino como na Classe Request como 'updatable = false'
				RequestState.CLOSED,
				request,
				owner);
		
		RequestStage createRequestStage = repository.save(requestStage);
		assertThat(createRequestStage.getId()).isEqualTo(1L);
	}
	
	@Test
	public void getByIdTest() {
		
		Optional<RequestStage> result = repository.findById(1L);
		
		assertThat(result.get().getDescription()).isEqualTo("Notebook Dell Comprado do site");
	}
	
	@Test
	public void listByRequestTest() {
		List<RequestStage> stage = repository.findAllByRequestId(1L); //id do Objeto request_id
		
		assertThat(stage.size()).isEqualTo(6); // quantidade da lista do id do Objeto requestStage
	}

}
