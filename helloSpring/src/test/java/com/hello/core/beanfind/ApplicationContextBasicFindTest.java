package com.hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {
	
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService",MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
		System.out.println("findBeanByName 실행완료");
	}
	
	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
		System.out.println("findBeanByType 실행완료");
	}

	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2() {
		MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
		System.out.println("findBeanByName2 실행완료");
	}
	
	@Test
	@DisplayName("빈 이름으로 조회 X")
	void findBeanByNameX() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class,
				() -> ac.getBean("xxxx",MemberService.class));
		System.out.println("findBeanByNameX 실행완료");
	}
	
}
