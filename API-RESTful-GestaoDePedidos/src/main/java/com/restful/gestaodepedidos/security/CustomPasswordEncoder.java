package com.restful.gestaodepedidos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.restful.gestaodepedidos.util.HashUtil;

@Component
public class CustomPasswordEncoder implements PasswordEncoder{

	//Codifica a senha bruta.
	@Override 
	public String encode(CharSequence rawPassword) {
		
		String hash = HashUtil.getSecureHash(rawPassword.toString());
		return hash;
	}
	
	/**
	 *Verifica se a senha codificada é a mesma que a bruta. 
	 * Retorna verdadeiro se as senhas coincidem e falso se não coincidem.
	 * A senha armazenada em si nunca é decodificada.
	 */
	
	@Override 
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		String hash = HashUtil.getSecureHash(rawPassword.toString());
		return hash.equals(encodedPassword);
	}

}
