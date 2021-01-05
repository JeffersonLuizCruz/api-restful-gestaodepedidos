package com.restful.gestaodepedidos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.RequestStage;
import com.restful.gestaodepedidos.domain.model.PageModel;
import com.restful.gestaodepedidos.domain.model.PageRequestModel;
import com.restful.gestaodepedidos.service.RequestService;
import com.restful.gestaodepedidos.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestController {
	
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService stageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody @Valid Request request){
		
		Request createRequest = requestService.save(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody @Valid Request request){
		
		request.setId(id);
		
		Request updateRequest = requestService.update(request);
		
		return ResponseEntity.ok(updateRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable Long id){
		
		Optional<Request> request = Optional.of(requestService.getById(id));
		
		return ResponseEntity.ok(request.get());
	}
	
//	@GetMapping
//	public ResponseEntity<List<Request>> listAll(){
//		
//		List<Request> requests = requestService.listAll();
//		
//		return ResponseEntity.ok(requests);
//	}
	
	@GetMapping  //Esse método é uma evolução do listAll()
	public ResponseEntity<PageModel<Request>> listAllByOnLazyModel(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		
		PageModel<Request> pm = requestService.listAllByOnLazyModel(pr);
		
		return ResponseEntity.ok(pm);
	}
	
		//http:localhost:8080/requests/1/request-stages
//	@GetMapping("/{id}/request-stages")
//	public ResponseEntity<List<RequestStage>> listAllStageById(@PathVariable Long id){
//		
//		List<RequestStage> stages = stageService.listAllByRequestId(id);
//		
//		return ResponseEntity.ok(stages);
//	
//	}
	//http:localhost:8080/requests/1/request-stages
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStageById(
			@PathVariable Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyModel(id, pr);
		
		return ResponseEntity.ok(pm);
	
	}

}
