package com.restful.gestaodepedidos.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.restful.gestaodepedidos.entities.Request;
import com.restful.gestaodepedidos.entities.RequestStage;
import com.restful.gestaodepedidos.entities.User;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */

@Getter @Setter
public class UserUpdateDto {
	
	@NotBlank(message = "Campo 'name' - não pode ser branco.")
	private String name;
	
	@Email(message = "Email inválido.")
	private String email;
	
	@Size(min = 6, max = 50, message = "Senha deve ser entre 6 a 50 caracteres.")
	private String password;
	
	private List<Request> request = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	
	//Esse método transforma essa classe ná classe User.
	public User transformToUser() {
		
		User user = new User(null, this.name, this.email, this.password, null, this.request, this.stages);
		return user;
	}

}
