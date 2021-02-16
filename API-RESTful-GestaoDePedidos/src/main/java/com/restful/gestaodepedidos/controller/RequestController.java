package com.restful.gestaodepedidos.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.restful.gestaodepedidos.dto.RequestDto;
import com.restful.gestaodepedidos.dto.RequestUpdateDto;
import com.restful.gestaodepedidos.security.AccessManager;
import com.restful.gestaodepedidos.service.RequestService;
import com.restful.gestaodepedidos.service.RequestStageService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "requests")
@Api("Controle de cadastro e consulta de pedidos")
public class RequestController {
	
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService stageService;
	@Autowired private AccessManager accessManager;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody @Valid RequestDto requestDto){
		Request request = requestDto.transformToRequest();
		Request createRequest = requestService.save(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createRequest);
	}
	
	@PreAuthorize("accessManager.isOwnerRequest(#id)")
	@CacheEvict(value = "requests", allEntries = true)
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody @Valid RequestUpdateDto requestDto){	
		Request request = requestDto.transformToRequest();
		request.setId(id);
		Request updateRequest = requestService.update(request);
		
		return ResponseEntity.ok(updateRequest);
	}
	
	@Cacheable(value = "requests")
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable Long id){
		Optional<Request> request = Optional.of(requestService.getById(id));
		
		return ResponseEntity.ok(request.get());
	}
	
	@Cacheable(value = "requests")
	@GetMapping 
	public ResponseEntity<PageModel<Request>> listAllByOnLazyModel(@RequestParam Map<String, String> params){
		PageRequestModel pr = new PageRequestModel(params);
		PageModel<Request> pm = requestService.listAllByOnLazyModel(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	//http:localhost:8080/requests/1/request-stages
	@Cacheable(value = "requests")
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStageById(@PathVariable Long id, @RequestParam Map<String, String> params){
		PageRequestModel pr = new PageRequestModel(params);
		PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyModel(id, pr);
		
		return ResponseEntity.ok(pm);
	
	}

}
