package com.restful.gestaodepedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restful.gestaodepedidos.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT FROM User WHERE email = ?1 AND password = ?2")
	public Optional<User> login(String email, String password);

}
