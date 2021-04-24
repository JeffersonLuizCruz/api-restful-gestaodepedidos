package com.restful.gestaodepedidos.services.exceptions;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


public class UnauthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public static final String MSG_UNAUTHORIZATION = "Credencial não autorizada";

}
