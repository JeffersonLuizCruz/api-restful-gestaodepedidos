package com.restful.gestaodepedidos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Request implements Serializable{

	private static final long serialVersionUID = -1118312724533576309L;
	
	private Long id;
	private String subject;
	private String description;
	private Date criationDate;
	private User user;
	private List<RequestStage> stage = new ArrayList<>();

}
