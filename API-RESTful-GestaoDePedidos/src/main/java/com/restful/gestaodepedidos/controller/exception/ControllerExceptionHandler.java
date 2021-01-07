package com.restful.gestaodepedidos.controller.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restful.gestaodepedidos.exception.NotFoundException;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NotFoundException.class)// Método personalizado
	public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException ex){
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	/*
	 * http://localhost:8080/users/login
	 * Body:
	 * {
    		"email": "jeffersongmail.com",
    		"password": "1234567"
		}
		
		O método handleMethodArgumentNotValid() personaliza erros de execução json.
		
		O método abaixo renderiza um valor errado da variável email: "email": "jeffersongmail.com" .
		String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	 * */
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		
		String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
