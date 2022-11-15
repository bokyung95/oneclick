package com.gang.oneclickshoppingmall.member.repository;

import com.gang.oneclickshoppingmall.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(String email);
}
