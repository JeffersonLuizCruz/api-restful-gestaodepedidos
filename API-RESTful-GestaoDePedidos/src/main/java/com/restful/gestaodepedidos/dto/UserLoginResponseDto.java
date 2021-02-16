package com.restful.gestaodepedidos.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
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
