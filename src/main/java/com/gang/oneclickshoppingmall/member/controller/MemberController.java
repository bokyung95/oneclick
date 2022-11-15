package com.gang.oneclickshoppingmall.member.controller;

import com.gang.oneclickshoppingmall.member.dto.LoginDto.LoginRequestDto;
import com.gang.oneclickshoppingmall.member.dto.SignUpDto;
import com.gang.oneclickshoppingmall.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/sign-up")
	public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpDto.SignUpRequestDto request) {
		return ResponseEntity.ok(memberService.registerMember(request));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto requestDto) {
		return ResponseEntity.ok(memberService.login(requestDto));
	}

}
