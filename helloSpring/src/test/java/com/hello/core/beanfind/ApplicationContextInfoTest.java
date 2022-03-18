package com.hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {
	
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//	@Test
//	@DisplayName("모든 빈 출력하기")
//	void findAllBean() {
//		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//		for(String beanDefinitionName : beanDefinitionNames) {
//			Object bean = ac.getBean(beanDefinitionName);
//			System.out.println("name : "+ beanDefinitionName + "object : "+bean);
//		}
//	}
	
	
	@Test
	@DisplayName("모든 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			
			//getRole : 내부에서 사용하는 빈을 구분할 수 있다.
			// Role ROLE_APPLICATION : 직접 등혹한 애플리케이션 빈
			// Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name : "+beanDefinitionName + "name : "+bean);
			}
		}
	}
	
}
