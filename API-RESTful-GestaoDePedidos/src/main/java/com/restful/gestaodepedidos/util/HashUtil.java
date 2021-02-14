package com.restful.gestaodepedidos.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
	
	//Método para gerar Hash de textos. Retorna um Hash de tamanho 64
	public static String getSecureHash(String text) {
		String hash = DigestUtils.sha3_256Hex(text);
		return hash;
	}

}
