package com.restful.gestaodepedidos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class UserLoginDto {
	
	@Email(message = "Endereço de E-mail inválido.")
	private String email;
	
	@Size(min = 6, message = "Senha deve ter no mínimo 6 digitos")
	@NotBlank(message = "Senha obrigatória.")
	private String password;

}
