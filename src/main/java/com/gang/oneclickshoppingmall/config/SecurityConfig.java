package com.gang.oneclickshoppingmall.config;

import com.gang.oneclickshoppingmall.jwt.JwtAuthenticationFilter;
import com.gang.oneclickshoppingmall.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// authenticationManager를 Bean 등록
	@Bean
	public AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable() // rest api 만을 고려하여 기본설정 해제
			.csrf()
			.ignoringAntMatchers("/h2-console/**")
			.disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용 안함
			.and()
			.authorizeRequests() // 요청에 대한 사용 권한 체크
			.antMatchers("/api/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/api/manager/**")
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class);
		// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣음

		http.headers().frameOptions().sameOrigin(); //h2 database가 iframe 사용하여 발생하는 오류 해결

		return http.build();
	}


}
