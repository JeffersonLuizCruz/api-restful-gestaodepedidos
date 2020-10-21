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
	
	private static final long serialVersionUID = 3641825375096925598L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 35, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "user")
	private List<Request> request = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<RequestStage> stage = new ArrayList<>();

}
