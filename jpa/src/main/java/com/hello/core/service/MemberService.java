package com.hello.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.domain.Member;
import com.hello.core.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)	// 읽기용으로 성능 최적화
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	
	//회원가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		}
	}
	
	//회원 전체조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId).get();
	}
	
	@Transactional
	public void update(Long id, String name) {
		Member member = memberRepository.findById(id).get();
		member.setName(name);
	}
}
