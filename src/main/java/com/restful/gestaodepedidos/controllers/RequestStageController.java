package com.restful.gestaodepedidos.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.gestaodepedidos.dto.RequestStageDto;
import com.restful.gestaodepedidos.entities.RequestStage;
import com.restful.gestaodepedidos.services.RequestStageService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "request-stages")
@Api("Status de abertura de um pedido(OPEN, IN_PROGRESS, CLOSED)")
public class RequestStageController {
	
	@Autowired private RequestStageService stageService;

	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageDto stageDto){
		RequestStage stage = stageDto.transformToRequestStage();
		RequestStage createStage = stageService.save(stage);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createStage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable Long id){
		RequestStage stage = stageService.getById(id);
		
		return ResponseEntity.ok(stage);
	}
	
	
}
