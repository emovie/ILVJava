package com.hello.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.DTO.JoinMemberDTO;
import com.hello.core.DTO.MemberDTO;
import com.hello.core.entity.Member;
import com.hello.core.entity.Member.MemberBuilder;
import com.hello.core.repository.MemberRepository;
import com.hello.core.service.MemberService;

import junit.framework.AssertionFailedError;

@SpringBootTest
@RunWith(SpringRunner.class)
class MemberRepositoryTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	@Transactional
	@Test
	@Rollback(false)
	public void repo_회원가입() throws Exception {
		//given
		Member member = Member.builder()
						.id("admin")
						.pw("admin")
						.userName("admin").build();
		
		//when
		String saveId = memberRepository.save(member);
		Member findmember = memberRepository.findOne(saveId);

		//then
		assertThat(findmember.getId()).isEqualTo(member.getId());
		assertThat(findmember.getUserName()).isEqualTo(member.getUserName());
		assertThat(findmember).isEqualTo(member);
	}

	@Transactional
	@Rollback(false)
	@Test
	public void 로그인() throws Exception {
		//given
		Member member = Member.builder()
						.id("admin")
						.pw("admin")
						.userName("admin").build();
		try {
			memberRepository.save(member);
		} catch (Exception e) {
			new Exception("회원가입에 실패했습니다.");
		}
		
		//when
		String username = null;
		try {
			username = memberRepository.login("admin", "admin");
		} catch (Exception e) {
			new Exception("로그인에 실패했습니다.");
		}
		
		//then
		assertThat(username).isNull();
		//assertThat(username).isEqualTo(member.getUsername());
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void serv_회원가입() throws Exception {
		//given
		Member member = Member.builder()
						.id("admin")
						.pw("admin")
						.userName("admin").build();
		
		JoinMemberDTO member2 = new JoinMemberDTO("admin", "admin", "admin"); 
				
		try {
			memberRepository.save(member);
		} catch (Exception e) {
			new Exception("회원가입에 실패했습니다.");
		}
		
		//when
		String joinId = memberService.join(member2);
		
		//then
		assertThat(member2.getUserName()).isEqualTo(joinId);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void serv_로그인() throws Exception {
		//given
		Member member = Member.builder()
						.id("admin")
						.pw("admin")
						.userName("admin").build();
		try {
			memberRepository.save(member);
		} catch (Exception e) {
			fail("회원가입에 실패했습니다.");
		}
		
		//when
		String userName = "";
		MemberDTO member2 = new MemberDTO("admin", "admin");
		try {
			userName = memberService.login(member2);
		}catch (Exception e) {
			assertThat(userName).isNotEqualTo(member.getUserName());
			fail("로그인에 실패했습니다.");
		}
		
		//then
		assertThat(userName).isEqualTo("admin");
	}
	
}