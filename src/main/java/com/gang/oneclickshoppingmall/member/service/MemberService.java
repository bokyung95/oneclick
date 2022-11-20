package com.gang.oneclickshoppingmall.member.service;

import static com.gang.oneclickshoppingmall.exception.type.ErrorCode.MEMBER_NOT_FOUND;

import com.gang.oneclickshoppingmall.exception.type.CustomException;
import com.gang.oneclickshoppingmall.jwt.JwtTokenProvider;
import com.gang.oneclickshoppingmall.member.dto.LoginDto.LoginRequestDto;
import com.gang.oneclickshoppingmall.member.dto.SignUpDto.SignUpRequestDto;
import com.gang.oneclickshoppingmall.member.entity.Member;
import com.gang.oneclickshoppingmall.member.repository.MemberRepository;
import com.gang.oneclickshoppingmall.member.type.MemberRole;
import com.gang.oneclickshoppingmall.member.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder bCryptPasswordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	public Long registerMember(SignUpRequestDto requestDto) {
		Member newMember = Member.builder()
			.username(requestDto.getUsername())
			.email(requestDto.getEmail())
			.password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
			.status(MemberStatus.ACTIVE)
			.roles(MemberRole.ROLE_USER)
			.build();

		memberRepository.save(newMember);

		return newMember.getId();
	}


	public String login(LoginRequestDto requestDto) {
		Member loginMember = memberRepository.findByEmail(requestDto.getEmail())
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

		return jwtTokenProvider.createToken(loginMember.getEmail(), loginMember.getRoles());
	}
}
