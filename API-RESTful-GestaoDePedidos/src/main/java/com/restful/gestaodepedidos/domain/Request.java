package com.restful.gestaodepedidos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restful.gestaodepedidos.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "request")
public class Request implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String subject;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column(name = "creation_date", nullable = false, updatable = false) // Essa data não pode ser mudada.Será fixa com uso do updatable=false
	@Temporal(TemporalType.TIMESTAMP) //Organiza a data em dd/mm/YYYY
	private Date creationDate;
	
	@Column(length = 75, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	/*Quando existe uma notação @ManyToOne esse atributo fará parte da coluna da base de dados
	 * Como uma chave-estrangeira
	 * */
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	
	/*Quando é @OneToMany dentro da Tabela do bancode dados 'request' não existirá
	 * uma coluna com o nome desse atribuito 'stages'. Esse é apenas uma referência
	 * ao @ManyToOne
	 * */
	@Getter(onMethod = @__(@JsonIgnore))//Ignora a requisição(ou serialização) Json
	@OneToMany(mappedBy = "request")
	private List<RequestStage> stages = new ArrayList<>();

}
