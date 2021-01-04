package com.restful.gestaodepedidos.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.dto.UserLoginDto;
import com.restful.gestaodepedidos.service.RequestService;
import com.restful.gestaodepedidos.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	
	
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid User user) {
		
		User createUser = userService.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,@Valid @RequestBody User user){
		
		user.setId(id);
		User updateUser = userService.save(user);
		
		return ResponseEntity.ok(updateUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		
		User user = userService.getById(id);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		
		List<User> users = userService.listAll();
		
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto user){
		
		User loggerUser = userService.login(user.getEmail(), user.getPassword());
		
		return ResponseEntity.ok(loggerUser);
	}
	
		//http:localhost:8080/users/1/requests
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestId(@PathVariable Long id){
		
		List<Request> requests = requestService.listAllByOwnerId(id);
		
		return ResponseEntity.ok(requests);
	}

}
