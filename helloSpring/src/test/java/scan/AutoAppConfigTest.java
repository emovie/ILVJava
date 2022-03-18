package scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;

public class AutoAppConfigTest {

	@Test
	public void basicScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberService memberService = ac.getBean(MemberService.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
	}
	
}
