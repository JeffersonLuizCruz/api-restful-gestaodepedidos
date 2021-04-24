package com.restful.gestaodepedidos.controllers.exceptions;

/**
 * @author Jefferson Luiz / jefferson.luiz.cruz@gmail.com
 *
 * */


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restful.gestaodepedidos.services.exceptions.NotFoundException;
import com.restful.gestaodepedidos.services.exceptions.UnauthorizationException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NotFoundException.class)// Método personalizado
	public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException ex){
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)// Método personalizado
	public ResponseEntity<ApiError> handlerNotFoundException(BadCredentialsException ex){
		
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), UnauthorizationException.MSG_UNAUTHORIZATION, new Date());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);	
	}
	
	@ExceptionHandler(AccessDeniedException.class)// Método personalizado
	public ResponseEntity<ApiError> handlerAccessDeniedException(AccessDeniedException ex){
		
		ApiError error = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);	
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		
		List<String> errors = new ArrayList<>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {errors.add(error.getDefaultMessage());
		});
		
		String defaultMessage = "Campo(s) inválido(s).";
		ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date(), errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
