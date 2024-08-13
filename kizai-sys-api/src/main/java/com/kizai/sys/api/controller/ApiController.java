package com.kizai.sys.api.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	// CSRFトークンを返すためだけのパス
		@GetMapping("/csrf")
		public CsrfToken csrf(CsrfToken token) {
			return token;
		}
}
