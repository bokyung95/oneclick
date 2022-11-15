package com.gang.oneclickshoppingmall.member.controller;

import com.gang.oneclickshoppingmall.member.dto.LoginDto.LoginRequestDto;
import com.gang.oneclickshoppingmall.member.dto.SignUpDto;
import com.gang.oneclickshoppingmall.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/api/sign-up")
	public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpDto.SignUpRequestDto request) {
		return ResponseEntity.ok(memberService.registerMember(request));
	}

	@PostMapping("/api/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto requestDto) {
		return ResponseEntity.ok(memberService.login(requestDto));
	}

}
