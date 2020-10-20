package com.restful.gestaodepedidos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User implements Serializable{
	
	private static final long serialVersionUID = 3641825375096925598L;
	
	private Long id;
	private String nome;
	private String email;
	private String password;
	private List<Request> request = new ArrayList<>();
	private List<RequestStage> stage = new ArrayList<>();

}
