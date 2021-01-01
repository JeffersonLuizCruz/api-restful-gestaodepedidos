package com.restful.gestaodepedidos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.enums.RequestState;

@Service
public class RequestService {
	
	@Autowired
	private RequestService repository;
	
	public Request save(Request request) {
		
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request saveRequest = repository.save(request);
		return saveRequest;
	}
	
	public Request update(Request update) {
		
		Request updateRequest = repository.save(update);
		return updateRequest;
	}
	
	public Optional<Request> getById(Long id) {
		
		Optional<Request> result = repository.getById(id);
		return result;
		}
	
	public List<Request> listAll(){
		
		List<Request> listRequest = repository.listAll();
		return listRequest;
	}
	
	public List<Request> listAllOwnerId(Long ownerId){
		
		List<Request> listOwner = repository.listAllOwnerId(ownerId);
		return listOwner;
	}

}
