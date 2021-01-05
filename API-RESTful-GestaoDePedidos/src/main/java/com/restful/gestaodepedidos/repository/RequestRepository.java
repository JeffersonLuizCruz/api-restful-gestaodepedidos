package com.restful.gestaodepedidos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	public List<Request> findAllByOwnerId(Long id);
	
	//paginação modo lazy
	public Page<Request> findAllByOwnerId(Long id, Pageable pageable);
	
	/*Método update: o método update vai ajudar a alterar o estado de um pedido quando
	for adicionar um novo estágio de pedido.
	Método que pode altar o estado do pedido e retornar o tal pedido modificado.
	 * */
	
	/*@Query: A query deve obdeser a ordem implicita dos paramétros com segue abaixo:
	 * */
	
	@Transactional(readOnly = false) // Dá permissão para alteração de uma coluna
	@Modifying
	@Query("UPDATE request SET state = ?2 WHERE id = ?1") // Diz para Spring JPA que foi criado um método especial
		//Altere a Tabela request da coluna state Quando o id for igual a ? 
	public int updateStatus(Long id, RequestState state);
							   //index 1 , index 2

}
