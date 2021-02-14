package com.restful.gestaodepedidos.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.RequestStage;
import com.restful.gestaodepedidos.domain.User;
import com.restful.gestaodepedidos.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestUpdateDto {
	

	@NotBlank(message = "Campo 'subject' - Obrigatório.")
	private String subject;
	
	private String description;
	
	@NotNull(message = "Campo 'state' - Obrigatório.")
	private RequestState state;
	
	@NotNull(message = "Campo 'owner' - Obrigatório.")
	private User owner;
	private List<RequestStage> stages = new ArrayList<>();
	
	public Request transformToRequest() {
		
		Request request = new Request(null, this.subject, this.description, null, this.state, this.owner, this.stages);
		return request;
	}

}
