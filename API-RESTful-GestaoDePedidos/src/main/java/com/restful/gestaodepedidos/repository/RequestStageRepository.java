package com.restful.gestaodepedidos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.gestaodepedidos.entities.RequestStage;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{
	
	public List<RequestStage> findAllByRequestId(Long id);
	
	public Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);

}
