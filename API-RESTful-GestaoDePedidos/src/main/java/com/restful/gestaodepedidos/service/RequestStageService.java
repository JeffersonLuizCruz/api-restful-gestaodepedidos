package com.restful.gestaodepedidos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.domain.RequestStage;
import com.restful.gestaodepedidos.domain.enums.RequestState;
import com.restful.gestaodepedidos.domain.model.PageModel;
import com.restful.gestaodepedidos.domain.model.PageRequestModel;
import com.restful.gestaodepedidos.exception.NotFoundException;
import com.restful.gestaodepedidos.repository.RequestRepository;
import com.restful.gestaodepedidos.repository.RequestStageRepository;

@Service
public class RequestStageService {
	
	@Autowired
	private RequestStageRepository stageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		
		stage.setRealizationDate(new Date());
		
		RequestStage createStage = stageRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
		
		return createStage;
	}
	
	public RequestStage getById(Long id) {
		
		Optional<RequestStage> result = stageRepository.findById(id);
		
		return result.orElseThrow(() -> new NotFoundException("Não existe RequestStage com id = " + id));
	}
	//http:localhost:8080/requests/1/request-stages
	public List<RequestStage> listAllByRequestId(Long requestId){
		
		List<RequestStage> listRequest = stageRepository.findAllByRequestId(requestId);
		
		return listRequest;
	}
	
	public PageModel<RequestStage> listAllByRequestIdOnLazyModel(Long requestId, PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize()); // cria paginação
		Page<RequestStage> page = stageRepository.findAllByRequestId(requestId, pageable);// inserir a paginação
		
		PageModel<RequestStage> pm = new PageModel<>(
				(int)page.getTotalElements(),
				page.getSize(),
				page.getTotalPages(),
				page.getContent());
		
		return pm;
	}

}
