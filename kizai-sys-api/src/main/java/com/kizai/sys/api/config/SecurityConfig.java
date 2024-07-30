
package com.kizai.sys.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig{
	
	@Value("${corsAllowSrc}")
	private String corsAllowSrc;
	
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//		.requestMatchers(HttpMethod.GET,"/test").permitAll()
//		.antMatchers(HttpMethod.GET,"/select").permitAll()
//		.antMatchers("/**").permitAll()
//		//					.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//		//					.and()
//		.cors().configurationSource(corsConfigurationSource())
//		.authorizeRequests()
//		.anyRequest().authenticated();
//
//		return http.build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authz) -> authz
				.requestMatchers("/user-info").permitAll()
				.requestMatchers("/user-info/{user_Id}").permitAll()
				.requestMatchers("/device-info").permitAll()
				.requestMatchers("/device-info/{device_id}").permitAll()
				.anyRequest().authenticated()
				);
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true); // クレデンシャル情報を許可するかどうかを設定する
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
