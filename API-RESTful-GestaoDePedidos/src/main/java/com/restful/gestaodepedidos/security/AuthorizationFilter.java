package com.restful.gestaodepedidos.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.gestaodepedidos.constant.SecurityConstants;
import com.restful.gestaodepedidos.controller.exception.ApiError;

import io.jsonwebtoken.Claims;

//OncePerRequestFilter garante que cada requisição uma é tratada uma de cada vez.
public class AuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION); //Busca o possível token que espera existir.
		
		/*Se este token estiver nulo ou não iniciar com "Dearer" da classe 
		 * SecurityConstants lance uma exception.
		 * */
		if(jwt == null || !jwt.startsWith(SecurityConstants.JWT_PROVIDER)) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), SecurityConstants.JWT_INVALID_MSG, new Date());
			//Se a autorização for negada na resposta será lençada uma exception
			PrintWriter writer = response.getWriter();
			
			//Converte o objeto apiError em uma string
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apiError);
			
			writer.write(apiErrorString);
			
			//Colocar algumas informações na resposta
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			return;
		}
		
		jwt = jwt.replace(SecurityConstants.JWT_PROVIDER, "");
		
		try {
			
			Claims claims = new JwtManager().parseToken(jwt);
			String email = claims.getSubject();
			List<String> roles = (List<String>) claims.get(SecurityConstants.JWT_ROLE_KEY);
			
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			roles.forEach(role -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
			});
			
			//Montar o autenticação do usuário para colocar no contexto de segurança.
			//É aqui que o usuário fica autenticado.
			Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} catch (Exception e) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), new Date());
			//Se a autorização for negada na resposta será lençada uma exception
			PrintWriter writer = response.getWriter();
			
			//Converte o objeto apiError em uma string
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apiError);
			
			writer.write(apiErrorString);
			
			//Colocar algumas informações na resposta
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			return;
		}
		
		filterChain.doFilter(request, response);
	}

}
