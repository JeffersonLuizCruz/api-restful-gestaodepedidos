package com.restful.gestaodepedidos.controller;


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
import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.domain.model.PageModel;
import com.restful.gestaodepedidos.domain.model.PageRequestModel;
import com.restful.gestaodepedidos.dto.UserLoginDto;
import com.restful.gestaodepedidos.service.RequestService;
import com.restful.gestaodepedidos.service.UserService;

@RestController
@RequestMapping(value = "users")
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
	
//	@GetMapping
//	public ResponseEntity<List<User>> listAll(){
//		
//		List<User> users = userService.listAll();
//		
//		return ResponseEntity.ok(users);
//	}
	
	@GetMapping  //Esse método é uma evolução do listAll()
	public ResponseEntity<PageModel<User>> listAll(
			/*defaultValue = "0" - caso o cliente não informe a paginação será paginado por padrão a página 0.
			 * Enquanto defaultValue = "10" - faz a paginação do tamanho 10. Isso evita o erro 400 Not found.
			 * */ 
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllByOnLazyModel(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto user){
		
		User loggerUser = userService.login(user.getEmail(), user.getPassword());
		
		return ResponseEntity.ok(loggerUser);
	}
	
//		//http://localhost:8080/users/1/requests-list /// Método Opcional
//	@GetMapping("/{id}/requests-list")
//	public ResponseEntity<List<Request>> listAllByRequestById(@PathVariable Long id){
//		
//		List<Request> requests = requestService.listAllByOwnerId(id);
//		
//		return ResponseEntity.ok(requests);
//	}
	
	//http:localhost:8080/users/1/requests /// Esse método é uma evolução do listAllByRequestId()
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllByRequestById(
			@PathVariable Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		PageRequestModel pr = new PageRequestModel(page, size); // cria paginação
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr); //inseri paginação

		return ResponseEntity.ok(pm);
	}
}
