package com.restful.gestaodepedidos.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*Resposta do login depois de autenticado.
 *A resposta tem informação do, token, tempo de expiração do token e o provedor do token.
 * 
 * */
@Getter
@AllArgsConstructor
public class UserLoginResponseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	private Long expire;
	private String tokenProvider;

}
