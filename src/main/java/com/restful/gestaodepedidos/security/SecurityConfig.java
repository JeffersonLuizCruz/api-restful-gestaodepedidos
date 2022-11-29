package com.restful.gestaodepedidos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restful.gestaodepedidos.services.UserService;

 
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
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
	
	@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.POST, "/users/login");
			super.configure(web);
		}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				   .antMatchers(HttpMethod.GET, "/").permitAll()
				   .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
				   .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
				   .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
				   .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
				   .antMatchers(HttpMethod.POST, "/users/login").permitAll()
				   .anyRequest().authenticated();

				   								 
		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	

}
