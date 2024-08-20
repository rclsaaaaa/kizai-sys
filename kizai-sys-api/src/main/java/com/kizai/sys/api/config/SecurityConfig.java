
package com.kizai.sys.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Value("${corsAllowSrc}")
	private String corsAllowSrc;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authz) -> authz
				.requestMatchers("/user-info").permitAll()
				.requestMatchers("/user-info/{user_id}").permitAll()
				.requestMatchers("/device-info").permitAll()
				.requestMatchers("/device-info/{device_id}").permitAll()
				.requestMatchers("/user-info/registration").permitAll()
				.requestMatchers("/device-info/update/{device_id}").permitAll()
				.requestMatchers("/api/csrf").permitAll()
				.requestMatchers("/user-login").permitAll()
				.anyRequest().authenticated()
				)
		.csrf((csrf) -> csrf
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringRequestMatchers("/api/csrf")
				.ignoringRequestMatchers("/device-info/update/{device_id}")
				.ignoringRequestMatchers("/user-info/registration")
				.ignoringRequestMatchers("/user-login")
				.ignoringRequestMatchers("/user-info/update")
				)
//		.cors((cors) -> cors
//				.configurationSource(corsConfigurationSource())
//				)
		;

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
