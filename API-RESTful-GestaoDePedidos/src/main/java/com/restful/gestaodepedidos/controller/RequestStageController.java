package com.restful.gestaodepedidos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.gestaodepedidos.domain.RequestStage;
import com.restful.gestaodepedidos.service.RequestStageService;

@RestController
@RequestMapping("request_stage")
public class RequestStageController {
	
	@Autowired private RequestStageService stageService;

	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage stage){
		
		RequestStage createStage = stageService.save(stage);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createStage);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable Long id){
		
		Optional<RequestStage> stage = stageService.getById(id);
		
		return ResponseEntity.ok(stage.get());
	}
	
	
}
