package com.gang.oneclickshoppingmall.member.entity;

import com.gang.oneclickshoppingmall.base.entity.BaseEntity;
import com.gang.oneclickshoppingmall.member.type.MemberRole;
import com.gang.oneclickshoppingmall.member.type.MemberStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String username;

	private String email;

	private String password;

	private LocalDate birthday;

	private String gender;

	@Enumerated(EnumType.STRING)
	private MemberRole roles;

	@Enumerated(EnumType.STRING)
	private MemberStatus status;

	private boolean emailAuth;

	private String emailAuthKey;

	private LocalDateTime deletedAt;

}
