// 리스트 19.1 자바로 스프링 웹 애플리케이션 활성화하기

package tacos.ingredients;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import tacos.IngredientServiceApplication;

public class IngredientServiceServletInitializer
		extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(
										SpringApplicationBuilder builder) {
		return builder.sources(IngredientServiceApplication.class);
	}
}
