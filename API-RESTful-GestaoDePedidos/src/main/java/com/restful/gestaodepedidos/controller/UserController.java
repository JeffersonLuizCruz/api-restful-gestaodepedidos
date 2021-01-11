package com.restful.gestaodepedidos.controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.restful.gestaodepedidos.dto.UpdateRoleDto;
import com.restful.gestaodepedidos.dto.UserLoginDto;
import com.restful.gestaodepedidos.dto.UserLoginResponseDto;
import com.restful.gestaodepedidos.dto.UserRequestDto;
import com.restful.gestaodepedidos.dto.UserUpdateDto;
import com.restful.gestaodepedidos.security.JwtManager;
import com.restful.gestaodepedidos.service.RequestService;
import com.restful.gestaodepedidos.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtManager jwtManager;
	
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserRequestDto userDto) {
		
		User userTosave = userDto.transformToUser();
		
		User createUser = userService.save(userTosave);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userDto){
		
		User user = userDto.transformToUser();
		
		user.setId(id);
		User updateUser = userService.update(user);
		
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
	
	@PostMapping("/login")// O token está retornando no corpo
	public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginDto user){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		////Depois de uma solicitação de login. Há um processamento de autenticação do token.
		Authentication auth = authManager.authenticate(token); 
		
		//Busca o usuário para criar seu token
		org.springframework.security.core.userdetails.User userSpring =
				(org.springframework.security.core.userdetails.User) auth.getPrincipal(); //O getPrincipal retorna o próprio usuário.
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities()
										.stream()
										.map(authority -> authority.getAuthority())
										.collect(Collectors.toList());
		
		/*Depois que a solicitação foi autenticada. A autenticação ficará armazenada
		 * */
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return ResponseEntity.ok(jwtManager.createToken(email,roles));
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
	public ResponseEntity<PageModel<Request>> listAllByRequestId(
			@PathVariable Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		PageRequestModel pr = new PageRequestModel(page, size); // cria paginação
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr); //inseri paginação

		return ResponseEntity.ok(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable Long id , @Valid @RequestBody UpdateRoleDto userDto){
		
		User user = new User();
		user.setId(id);
		user.setRole(userDto.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build();
	}
}
