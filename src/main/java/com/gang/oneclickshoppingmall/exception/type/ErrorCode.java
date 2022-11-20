package com.gang.oneclickshoppingmall.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	MEMBER_NOT_FOUND("사용자가 없습니다."),
	ALREADY_MEMBER_EMAIL_EXIST("이미 가입된 이메일이 존재합니다."),
	ALREADY_MEMBER_USERNAME_EXIST("이미 가입된 아이디가 존재합니다."),
	PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다.")
	;

	private final String description;
}
