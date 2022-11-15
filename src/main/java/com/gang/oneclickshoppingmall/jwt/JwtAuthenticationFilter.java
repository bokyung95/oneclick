package com.gang.oneclickshoppingmall.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		// 헤더에서 JWT를 받아옴
		String token = jwtTokenProvider.resolveToken(request);
		// 유효한 토큰인지 확인
		if (token != null && jwtTokenProvider.validateToken(token)) {
			// 토큰이 유효하면 토큰으로부터 유저 정보를 받아옴
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			// SecurityContext에 Authentication 객체를 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

}
