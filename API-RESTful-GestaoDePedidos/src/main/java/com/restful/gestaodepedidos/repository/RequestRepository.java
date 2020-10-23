package com.restful.gestaodepedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	public List<Request> findByOwnerId(Long id);
	
	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	public Request update(Long id, RequestState state);

}
