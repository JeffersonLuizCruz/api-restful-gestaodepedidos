package com.restful.gestaodepedidos.dto;

import javax.validation.constraints.NotNull;

import com.restful.gestaodepedidos.entities.Request;
import com.restful.gestaodepedidos.entities.RequestStage;
import com.restful.gestaodepedidos.entities.User;
import com.restful.gestaodepedidos.enums.RequestState;

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
public class RequestStageDto {
	
	@NotNull(message = "Campo 'description' - Obritatório. - RequestStage")
	private String description;
	
	@NotNull(message = "Campo 'state' - Obritatório.")
	private RequestState state;
	
	@NotNull(message = "Campo 'request' - Obritatório.")
	private Request request;
	
	@NotNull(message = "Campo 'owner' - Obritatório.")
	private User owner;
	
	public RequestStage transformToRequestStage() {
		
		RequestStage stage = new RequestStage(null, this.description, null, this.state, this.request, this.owner);
		
		return stage;
	}

}
