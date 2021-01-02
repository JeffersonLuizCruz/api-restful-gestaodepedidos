package com.restful.gestaodepedidos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.enums.RequestState;
import com.restful.gestaodepedidos.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	public Request save(Request request) {
		
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request saveRequest = requestRepository.save(request);
		return saveRequest;
	}
	
	public Request update(Request update) {
		
		Request updateRequest = requestRepository.save(update);
		return updateRequest;
	}
	
	public Optional<Request> getById(Long id) {
		
		Optional<Request> result = requestRepository.findById(id);
		return result;
		}
	
	public List<Request> listAll(){
		
		List<Request> listRequest = requestRepository.findAll();
		return listRequest;
	}
	
	public List<Request> listAllOwnerId(Long ownerId){
		
		List<Request> listOwner = requestRepository.findAllByOwnerId(ownerId);
		return listOwner;
	}

}
