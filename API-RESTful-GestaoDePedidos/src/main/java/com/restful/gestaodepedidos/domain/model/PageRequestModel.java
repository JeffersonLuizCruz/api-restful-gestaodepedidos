package com.restful.gestaodepedidos.domain.model;

import java.util.Map;

import org.springframework.data.domain.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PageRequestModel {
	/*Classe que ajuda a requisitar uma pagina
	 * */	
	private int page = 0; // Qual página quero requisitar. Se é a 1, 2 ou a 3 ...
	private int size = 10; //
	private String search = "";
	
	public PageRequestModel(Map<String, String> params) {
		
		if(params.containsKey("page")) page = Integer.parseInt(params.get("page"));
		if(params.containsKey("size")) size = Integer.parseInt(params.get("size"));
		if(params.containsKey("search")) search = params.get("search");
	}
	
	public PageRequest toSpringPageRequest() {
		
		return PageRequest.of(page, size);
	}

}
