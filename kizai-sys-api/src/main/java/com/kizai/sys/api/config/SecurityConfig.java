package com.kizai.sys.api.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kizai.sys.api.model.entity.CustomUserDetails;
import com.kizai.sys.api.service.impl.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	//	@Value("${corsAllowSrc}")
	//	private String corsAllowSrc;

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
				.requestMatchers("/user-info/auth").permitAll()
				.requestMatchers("/user-info/password-reset/{user_id}").permitAll()
				.requestMatchers("/user-info/password-reset/auth").permitAll()
				.anyRequest().authenticated()
				)
		.csrf((csrf) -> csrf
				//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringRequestMatchers("/api/csrf")
				.ignoringRequestMatchers("/device-info/update/{device_id}")
				.ignoringRequestMatchers("/user-info/registration")
				.ignoringRequestMatchers("/user-login")
				.ignoringRequestMatchers("/user-info/update")
				.ignoringRequestMatchers("/user-info/auth")
				.ignoringRequestMatchers("/user-info/password-reset/{user_id}")
				.ignoringRequestMatchers("/user-info/password-reset/auth")
				)
		//		.cors((cors) -> cors
		//				.configurationSource(corsConfigurationSource())
		//				)
		.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) //セッションが必要な場合に作成
//				.sessionFixation().newSession() // セッション固定攻撃の対策
//				.maximumSessions(1) // 最大セッション数の制限
//				.maxSessionsPreventsLogin(true) // 同時ログインの制限（新しいログインを防ぐ）
				)
				.authenticationProvider(authenticationProvider());


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
	
	@Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsServiceImpl);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher(); // セッションイベントをリスンするBeanを登録
	}
}
