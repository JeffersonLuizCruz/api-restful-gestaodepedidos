package com.restful.gestaodepedidos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PageRequestModel {
	/*Classe que ajuda a requisitar uma pagina
	 * 
	 * */
	
	private int page; // Qual página quero requisitar. Se é a 1, 2 ou a 3 ...
	private int size; // 

}
