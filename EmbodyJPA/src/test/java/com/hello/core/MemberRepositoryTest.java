package com.hello.core;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.Repository.MemberRepository;
import com.hello.core.domain.Member;
import com.hello.core.service.MemberService;

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
		Member member = new Member();
		member.setId("admin");
		member.setPw("admin");
		member.setUsername("admin");
		
		//when
		String saveId = memberRepository.save(member);
		Member findmember = memberRepository.findOne(saveId);

		//then
		assertThat(findmember.getId()).isEqualTo(member.getId());
		assertThat(findmember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findmember).isEqualTo(member);
	}

	@Transactional
	@Rollback(false)
	@Test
	public void 로그인() throws Exception {
		//given
		Member member = new Member();
		member.setId("admin");
		member.setPw("admin1234");
		member.setUsername("admin");
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
		Member member = new Member();
		member.setId("admin");
		member.setPw("admin1234");
		member.setUsername("admin");
		
		Member member2 = new Member();
		member2.setId("admin2");
		member2.setPw("admin2222");
		member2.setUsername("admin2");
		
		try {
			memberRepository.save(member);
		} catch (Exception e) {
			new Exception("회원가입에 실패했습니다.");
		}
		
		//when
		String joinId = memberService.join(member2);
		
		//then
		assertThat(member2.getUsername()).isEqualTo(joinId);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void serv_로그인() throws Exception {
		//given
		Member member = new Member();
		member.setId("admin");
		member.setPw("admin1234");
		member.setUsername("admin");
		
		try {
			memberRepository.save(member);
		} catch (Exception e) {
			new Exception("회원가입에 실패했습니다.");
		}
		
		//when
		String userName = memberService.login(member);
		
		//then
		assertThat(userName).isEqualTo("admin");
	}
	
}