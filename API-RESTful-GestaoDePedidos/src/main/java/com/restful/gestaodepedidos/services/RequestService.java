package com.restful.gestaodepedidos.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.entities.Request;
import com.restful.gestaodepedidos.enums.RequestState;
import com.restful.gestaodepedidos.repository.RequestRepository;
import com.restful.gestaodepedidos.services.exceptions.NotFoundException;
import com.restful.gestaodepedidos.services.pagemodel.PageModel;
import com.restful.gestaodepedidos.services.pagemodel.PageRequestModel;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */

@Service
public class RequestService {
	
	@Autowired private RequestRepository requestRepository;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(Instant.now());
		
		Request saveRequest = requestRepository.save(request);
		return saveRequest;
	}
	
	public Request update(Request update) {		
		Request updateRequest = requestRepository.save(update);
		return updateRequest;
	}
	
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Não existe Request com id = " + id));
		}
	
		public PageModel<Request> listAllByOnLazyModel(PageRequestModel pr) {
			Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize()); 
			Page<Request> page = requestRepository.findAll(pageable);

			PageModel<Request> pm = new PageModel<>(
										(int) page.getTotalElements(),
										page.getSize(),
										page.getTotalPages(),
										page.getContent());
			return pm;
		}
	
	//http://localhost:8080/users/1/requests
	public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
		
		PageModel<Request> pm = new PageModel<>(
				(int)page.getTotalElements(),
				page.getSize(),
				page.getTotalPages(),
				page.getContent());
		
		return pm;
	}

}
