package scan.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
	
	// BeanA = include
	// BeanB = execlude

	@Test
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ac.getBean("beanA",BeanA.class);
		assertThat(beanA).isNotNull();
		
		assertThrows(NoSuchBeanDefinitionException.class,
				() -> ac.getBean("beanB",BeanB.class));
	}
	
	@Configuration
	@ComponentScan(
			includeFilters = @Filter(classes = MyIncludeComponent.class),
			excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExecludeComponent.class)
	)
	static class ComponentFilterAppConfig {
		
	}
	
}
