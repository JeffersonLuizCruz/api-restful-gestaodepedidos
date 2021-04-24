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

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


//Classe de autorização de acesso
@Component(value = "accessManager")
public class AccessManager {
	
	@Autowired private UserRepository userRepository;
	@Autowired private RequestService requestService;
	
	//verifica o usuário a modificar se é exatamente igual ao usuário que há no token.
	public boolean isOwner(Long id) {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("Sem permissão" + email);
		
		User user = result.get();
		
		return user.getId() == id;
	}
	
	//verifica se o usuário presente no token é exatamente igual ao usuário do pedido.
	public boolean isOwnerRequest(Long id) {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("Sem permissão" + email);
		
		User user = result.get();
		
		Request request = requestService.getById(id);
		
		return user.getId() == request.getOwner().getId();
	}

}
