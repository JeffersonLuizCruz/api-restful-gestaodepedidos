package com.restful.gestaodepedidos.dto;

import javax.validation.constraints.NotNull;

import com.restful.gestaodepedidos.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UpdateRoleDto {
	
	@NotNull(message = "Campo 'role'- Valor não pode ser nulo.")
	private Role role;

}
