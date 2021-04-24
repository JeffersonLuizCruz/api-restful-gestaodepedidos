package com.restful.gestaodepedidos.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.entities.User;
import com.restful.gestaodepedidos.repository.UserRepository;
import com.restful.gestaodepedidos.services.exceptions.NotFoundException;
import com.restful.gestaodepedidos.services.pagemodel.PageModel;
import com.restful.gestaodepedidos.services.pagemodel.PageRequestModel;
import com.restful.gestaodepedidos.specification.UserSpecification;
import com.restful.gestaodepedidos.util.HashUtil;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */

@Service
public class UserService implements UserDetailsService{
	
	@Autowired private UserRepository userRepository;

	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword()); 
		user.setPassword(hash); 
		
		User createUser = userRepository.save(user);
		
		return createUser;
	}
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User updateUser = userRepository.save(user);
		
		return updateUser;
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Não existe usuário com id = " + id));
	}
	
	public List<User> listAll() {
		List<User> users = userRepository.findAll();
		
		return users;		
	}
	
	public PageModel<User> listAllByOnLazyModel(PageRequestModel pr){
		Pageable pageable = pr.toSpringPageRequest();
		Specification<User> spec = UserSpecification.search(pr.getSearch());
		
		Page<User> page = userRepository.findAll(spec, pageable);
		
		PageModel<User> pm = new PageModel<>(
							(int)page.getTotalElements(),
							page.getSize(),
							page.getTotalPages(),
							page.getContent());
		
		return pm;
	}
	
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password); 
				
		Optional<User> access = userRepository.login(email, password); 
		
		return access.get();
	}
	
	public int updateRole(User user) {
		return userRepository.updateRole(user.getId(), user.getRole());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByEmail(username);
		if(!result.isPresent()) throw new UsernameNotFoundException("Email não cadastrado: " + result);
		
		User user = result.get();
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

		org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		
		return userSpring;
	}

}
