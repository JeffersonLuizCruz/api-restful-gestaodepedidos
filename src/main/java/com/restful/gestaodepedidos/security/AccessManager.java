package com.restful.gestaodepedidos.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.restful.gestaodepedidos.entities.Request;
import com.restful.gestaodepedidos.entities.User;
import com.restful.gestaodepedidos.repository.UserRepository;
import com.restful.gestaodepedidos.services.RequestService;
import com.restful.gestaodepedidos.services.exceptions.NotFoundException;


@Component(value = "accessManager")
public class AccessManager {
	
	@Autowired private UserRepository userRepository;
	@Autowired private RequestService requestService;
	
	
	public boolean isOwner(Long id) {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("Sem permissão" + email);
		
		User user = result.get();
		
		return user.getId() == id;
	}
	
	
	public boolean isOwnerRequest(Long id) {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("Sem permissão" + email);
		
		User user = result.get();
		
		Request request = requestService.getById(id);
		
		return user.getId() == request.getOwner().getId();
	}

}
