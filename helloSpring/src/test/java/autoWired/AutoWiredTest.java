package autoWired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

public class AutoWiredTest {

	@Test
	void AutoWiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}
	
	static class TestBean {
		
		@Autowired(required = false)		// required는 기본값이 true
		public void setNoBean1(Member member1) {
			System.out.println("member1 : "+member1);
		}
		
		@Autowired
		public void setNoBean2(@Nullable Member member2) {
			System.out.println("member2 : "+member2);
		}
		
		@Autowired
		public void setNoBean3(Optional<Member> member3) {
			System.out.println("member3 : "+member3);
		}
		
	}
	
}
