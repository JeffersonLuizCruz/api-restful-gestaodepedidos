package com.restful.gestaodepedidos.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.restful.gestaodepedidos.security.AccessManager;
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
	@Autowired private AccessManager accessManager;
	
	
	
	@Secured({"ROLE_ADMINISTRATOR"})
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserRequestDto userDto) {
		User userTosave = userDto.transformToUser();
		User createUser = userService.save(userTosave);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
		}
	
	@PreAuthorize("@accessManager.isOwner(#id)")
	@CacheEvict(value = "users", allEntries = true)
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userDto){
		User user = userDto.transformToUser();
		
		user.setId(id);
		User updateUser = userService.update(user);
		
		return ResponseEntity.ok(updateUser);
	}
	
	@Cacheable("users")
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		User user = userService.getById(id);
		
		return ResponseEntity.ok(user);
	}
	
	@Cacheable("users")
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
		@RequestParam Map<String, String> params){
		PageRequestModel pr = new PageRequestModel(params);
		PageModel<User> pm = userService.listAllByOnLazyModel(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginDto user){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

		Authentication auth = authManager.authenticate(token); 
		
		org.springframework.security.core.userdetails.User userSpring =
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities()
										.stream()
										.map(authority -> authority.getAuthority())
										.collect(Collectors.toList());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return ResponseEntity.ok(jwtManager.createToken(email,roles));
	}
	
	//http:localhost:8080/users/1/requests 
	@Cacheable("users")
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllByRequestId(
			@PathVariable Long id,
			@RequestParam Map<String, String> params) {

		PageRequestModel pr = new PageRequestModel(params);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr); 

		return ResponseEntity.ok(pm);
	}
	
	@Secured({"ROLE_ADMINISTRATOR"})
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable Long id , @Valid @RequestBody UpdateRoleDto userDto){
		User user = new User();
		user.setId(id);
		user.setRole(userDto.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build();
	}
}
