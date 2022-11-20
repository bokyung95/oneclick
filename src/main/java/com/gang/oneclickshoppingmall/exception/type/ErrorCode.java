package com.gang.oneclickshoppingmall.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	MEMBER_NOT_FOUND("사용자가 없습니다.");

	private final String description;
}
