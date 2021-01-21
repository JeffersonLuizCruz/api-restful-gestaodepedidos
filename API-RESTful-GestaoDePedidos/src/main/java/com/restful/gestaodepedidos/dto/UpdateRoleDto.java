package com.restful.gestaodepedidos.dto;

import javax.validation.constraints.NotNull;

import com.restful.gestaodepedidos.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UpdateRoleDto {
	
	@NotNull(message = "Campo 'role'- Valor não pode ser nulo.")
	private Role role;

}
