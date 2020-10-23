package com.restful.gestaodepedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.gestaodepedidos.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	public List<Request> findByOwnerId(Long id);

}
