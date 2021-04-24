package com.restful.gestaodepedidos.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.restful.gestaodepedidos.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


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
	
	/**
	 * @Column(updatable = false) -> É uma data fixa. Não sofre alteração.
	 * */
	@Column(name = "realization_date", nullable = false, updatable = false) 
	private Instant realizationDate;
	
	@Column(length = 75, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	private Request request;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;


}
