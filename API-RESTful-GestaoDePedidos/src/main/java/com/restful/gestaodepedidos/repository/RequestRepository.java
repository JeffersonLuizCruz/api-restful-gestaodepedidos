package com.restful.gestaodepedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	public List<Request> findAllByOwnerId(Long id);
	
	/*Método update: o método update vai ajudar a alterar o estado de um pedido quando
	for adicionar um novo estágio de pedido.
	Método que pode altar o estado do pedido e retornar o tal pedido modificado.
	 * */
	
	/*@Query: A query deve obdeser a ordem implicita dos paramétros com segue abaixo:
	 * */
	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	public Request updateStatus(Long id, RequestState state);
							   //index 1 , index 2

}
