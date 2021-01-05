package com.restful.gestaodepedidos.domain.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PageModel<E> implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int totalElements; // Numa página posso ter um número total de elementos
	private int pageSize; // Tamanho da página
	private int totalPages; // Número total de páginas;
	private List<E> elements; // Lista genérica. Podeserá ser uma lista de usuário, request e stage.
}
