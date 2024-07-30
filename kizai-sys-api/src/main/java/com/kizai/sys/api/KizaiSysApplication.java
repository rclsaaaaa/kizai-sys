package com.kizai.sys.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.kizai.sys.api.repository")
public class KizaiSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(KizaiSysApplication.class, args);
	}

}
