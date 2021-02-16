package com.restful.gestaodepedidos.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
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
	
	/**
	 * @Column(updatable = false) -> É uma data fixa. Não sofre alteração.
	 * */
	@Column(name = "creation_date", nullable = false, updatable = false) 
	private Instant creationDate;
	
	@Column(length = 75, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	
	/**
	 * Nota: Notação depreciada
	 * @Getter(onMethod = @__(@JsonIgnore)) -> Ignora o atributo durante a serialização.
	 * @Setter(onMethod = @__(@JsonProperty) -> Deserializa o atributo json para objeto java. 
	 * 
	 * Substituída:
	 * 
	 *  @Getter(onMethod_=@JsonIgnore)
	 *  @Setter(onMethod_=@JsonProperty)
	 * */
	@Getter(onMethod = @__(@JsonIgnore))//Ignora a requisição durante  serialização para Json
	@OneToMany(mappedBy = "request")
	private List<RequestStage> stages = new ArrayList<>();

}
