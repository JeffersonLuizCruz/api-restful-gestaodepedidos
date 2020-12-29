package com.restful.gestaodepedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.gestaodepedidos.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{
	
	public List<RequestStage> findAllByRequestId(Long id);

}
