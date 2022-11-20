package com.gang.oneclickshoppingmall.jwt;

import com.gang.oneclickshoppingmall.exception.CustomException;
import com.gang.oneclickshoppingmall.exception.type.ErrorCode;
import com.gang.oneclickshoppingmall.member.entity.Member;
import com.gang.oneclickshoppingmall.member.entity.MemberDetails;
import com.gang.oneclickshoppingmall.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		return new MemberDetails(member);
	}
}
