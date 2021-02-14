package com.restful.gestaodepedidos.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.restful.gestaodepedidos.util.HashUtil;

@ExtendWith(SpringExtension.class)
public class HashUtilTest {
	
	@Test
	public void getSecureHashTest() {
		
		String hash = HashUtil.getSecureHash("123");
		System.out.println(hash);
		
		assertThat(hash.length()).isEqualTo(64);
	}
	

}
