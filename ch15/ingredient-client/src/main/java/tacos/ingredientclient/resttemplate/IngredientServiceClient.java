package tacos.ingredientclient.resttemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import tacos.Ingredient;

public class IngredientServiceClient {
	
	private RestTemplate rest;
	
	public IngredientServiceClient(@LoadBalanced RestTemplate rest) {
		this.rest = rest;
	}
	
//	public Ingredient getIngredientById(String ingredientId) {
//		return rest.getForObject("http://localhost:8080/ingredients/{id}",
//									Ingredient.class, ingredientId);
//	}
	
	public Ingredient getIngredientById(String ingredientId) {
		return rest.getForObject("http://ingredient-service/ingredients/{id}",
									Ingredient.class, ingredientId);
	}
	
//	@HystrixCommand(fallbackMethod="getDefaultIngredients")
//	@HystrixCommand(
//		fallbackMethod="getDefaultIngredients",
//		commandProperties= {
//			@HystrixProperty(
//				name="execution.iolation.thread.timeoutInMilliseconds",
//				value="500")
//		})
//	@HystrixCommand(
//		fallbackMethod="getDefaultIngredients",
//		commandProperties= {
//			@HystrixProperty(
//				name="execution.timeout.enabled",
//				value="false")
//		})
	@HystrixCommand(
		fallbackMethod="getDefaultIngredients",
		commandProperties= {
			@HystrixProperty(
				name="circuitBreaker.requestVolumeThreshold",
				value="30"),
			@HystrixProperty(
				name="circuitBreaker.errorThresholdPercentage",
				value="25"),
			@HystrixProperty(
				name="metrics.rollingStats.timeInMilliseconds",
				value="20000"),
			@HystrixProperty(
				name="circuitBreaker.sleepWindowInMilliseconds",
				value="60000"),
		})
	public Iterable<Ingredient> getAllIngredients() {
		ParameterizedTypeReference<List<Ingredient>> stringList =
			new ParameterizedTypeReference<List<Ingredient>>() {};
		return rest.exchange(
			"http://ingredient-service/ingredients", HttpMethod.GET,
			HttpEntity.EMPTY, stringList).getBody();
	}
	
	private Iterable<Ingredient> getDefaultIngredients() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient(
				"FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
		ingredients.add(new Ingredient(
				"GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
		ingredients.add(new Ingredient(
				"CHED", "Shredded Cheddar", Ingredient.Type.CHEESE));
		return ingredients;
	}
}
