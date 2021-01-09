package com.restful.gestaodepedidos.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.domain.model.PageModel;
import com.restful.gestaodepedidos.domain.model.PageRequestModel;
import com.restful.gestaodepedidos.exception.NotFoundException;
import com.restful.gestaodepedidos.repository.UserRepository;
import com.restful.gestaodepedidos.util.HashUtil;

/*Seria interessante essa camada de serviço ter uma implementação de interface com todos os métodos
 * */
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		/*Antes de criar o usuário é necessário criar o hash da senha do usuário
		 * */
		String hash = HashUtil.getSecureHash(user.getPassword()); // Hash da senha do usuário gerada.
		user.setPassword(hash); // Hash da senha do usuário inserida.
		
		User createUser = userRepository.save(user);
		return createUser;
	}
	
	public User update(User user) {
		/*Antes de criar o usuário é necessário criar o hash da senha do usuário
		 * */
		String hash = HashUtil.getSecureHash(user.getPassword()); // Hash gerado do usuário
		user.setPassword(hash); // Hash inserido no usuário
		
		User updateUser = userRepository.save(user);
		return updateUser;
	}
	
	public User getById(Long id) {
		
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Não existe Owner com id = " + id));
	}
	
	public List<User> listAll() {
		
		List<User> users = userRepository.findAll();
		return users;		
	}
	
	public PageModel<User> listAllByOnLazyModel(PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize()); // cria a paginação
		Page<User> page = userRepository.findAll(pageable); // inseri os dados numa pagina.
		
		PageModel<User> pm = new PageModel<>(
				(int)page.getTotalElements(),
				page.getSize(), page.getTotalPages(),
				page.getContent());
		
		return pm;
	}
	
	public User login(String email, String password) {
		/*Criação do Hash da senha do usuário e inserida na própria senha
		 * */
		password = HashUtil.getSecureHash(password); // Hash gerado da senha
				
		Optional<User> access = userRepository.login(email, password); // Hash inserido
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
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE " + user.getRole().name()));
		
		org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		return userSpring;
	}

}
