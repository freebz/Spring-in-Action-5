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
