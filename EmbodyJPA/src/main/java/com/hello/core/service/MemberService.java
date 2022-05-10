package com.hello.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.DTO.JoinMemberDTO;
import com.hello.core.DTO.MemberDTO;
import com.hello.core.entity.Member;
import com.hello.core.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional
	public String join(JoinMemberDTO member) throws Exception {
		validateDuplicate(member);
		
		Member saveMember = Member.builder()
							.id(member.getId())
							.pw(member.getPw())
							.userName(member.getUserName()).build();
		
		return memberRepository.save(saveMember);
	}
	
	private void validateDuplicate(JoinMemberDTO member) throws Exception {
		List<Member> findMembers = memberRepository.findById(member.getId());
		if(!findMembers.isEmpty()) throw new IllegalStateException("이미 존재하는 회원입니다.");
	}
	
	@Transactional
	public String login(MemberDTO member) throws Exception {
		String username = memberRepository.login(member.getId(), member.getPw());
		return (username != null ? username : "");
	}
	
}