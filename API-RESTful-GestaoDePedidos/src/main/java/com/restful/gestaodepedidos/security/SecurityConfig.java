package com.restful.gestaodepedidos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.restful.gestaodepedidos.service.UserService;

/*WebSecurityConfigurerAdapter responsável por toda a segurança
 *(protegendo os URLs do aplicativo, validando o nome de usuário
 *e as senhas enviadas, redirecionando para o formulário de login, etc) dentro do seu aplicativo. 
 * 
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired private UserService userService;
	@Autowired private  CustomPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.userDetailsService(userService)
		.passwordEncoder(passwordEncoder);
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
