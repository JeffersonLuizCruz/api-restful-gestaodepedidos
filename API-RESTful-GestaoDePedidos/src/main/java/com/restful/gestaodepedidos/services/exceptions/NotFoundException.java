package com.restful.gestaodepedidos.services.exceptions;


/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */

public class NotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String msg) {
		super(msg);
	}

}
