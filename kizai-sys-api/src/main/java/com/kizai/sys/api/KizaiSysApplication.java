package com.kizai.sys.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan(basePackages = "com.kizai.sys.api.repository")
//@EnableWebSecurity(debug = true)
public class KizaiSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(KizaiSysApplication.class, args);
	}

}
