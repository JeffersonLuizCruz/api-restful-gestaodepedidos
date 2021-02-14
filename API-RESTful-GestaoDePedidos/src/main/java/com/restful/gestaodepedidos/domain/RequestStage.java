package com.restful.gestaodepedidos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.restful.gestaodepedidos.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "request_stage")
public class RequestStage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column(name = "realization_date", nullable = false, updatable = false) // Essa data não pode ser mudada.Será fixa
	@Temporal(TemporalType.TIMESTAMP)//Padrão com Hora/Data
	private Date realizationDate;
	
	@Column(length = 75, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	/*Quando existe uma notação @ManyToOne esse atributo fará parte da coluna da base de dados
	 * Como uma chave-estrangeira
	 * */
	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	private Request request;
	
	/*Quando existe uma notação @ManyToOne esse atributo fará parte da coluna da base de dados
	 * Como uma chave-estrangeira
	 * */
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner; 


}
