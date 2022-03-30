package com.hello.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.Repository.MemberRepository;
import com.hello.core.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional
	public String join(Member member) {
		validateDuplicate(member);
		return memberRepository.save(member);
	}
	
	private void validateDuplicate(Member member) {
		List<Member> findMembers = memberRepository.findById(member.getId());
		if(!findMembers.isEmpty()) throw new IllegalStateException("이미 존재하는 회원입니다.");
	}
	
	@Transactional
	public String login(Member member) {
		return memberRepository.login(member.getId(), member.getPw());
	}
	
}