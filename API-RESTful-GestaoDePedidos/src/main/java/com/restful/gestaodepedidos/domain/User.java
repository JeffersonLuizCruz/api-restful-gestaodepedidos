package com.restful.gestaodepedidos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.restful.gestaodepedidos.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false, unique = true)
	@Email
	private String email;
	
	@Getter(onMethod = @__(@JsonIgnore))//Ignora a requisição(ou serialização) Json
	@Setter(onMethod = @__(@JsonProperty))//Ignora a resposta(ou deserialização) Json
	@Column(nullable = false)
	@Size(min = 6)
	private String password;
	
	@Column(length = 35, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	/*OBS: Quando é @OneToMany dentro da Tabela do bancode dados 'user' não existirá
	 * uma coluna com o nome desse atribuito 'request'. Esse é apenas uma referência
	 * ao @ManytoOne
	 * */
	@Getter(onMethod = @__(@JsonIgnore)) //Ignora a requisição durante  serialização para Json
	@OneToMany(mappedBy = "owner") 
	private List<Request> request = new ArrayList<>();
	
	/*Quando é @OneToMany dentro da Tabela do bancode dados 'user' não existirá
	 * uma coluna com o nome desse atribuito 'stages'. Esse é apenas uma referência
	 * ao @ManyToOne
	 * */
	@Getter(onMethod = @__(@JsonIgnore))//Ignora a requisição durante  serialização para Json
	@OneToMany(mappedBy = "owner")
	private List<RequestStage> stages = new ArrayList<>();

}
