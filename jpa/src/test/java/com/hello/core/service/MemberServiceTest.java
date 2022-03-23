package com.hello.core.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.domain.Member;
import com.hello.core.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberMemberRepository;
	@Autowired EntityManager em;
	
	@Test
	public void join() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		em.flush();
		assertEquals(member, memberMemberRepository.findOne(saveId));
	}

}
