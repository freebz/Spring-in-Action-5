package tacos.ingredientclient.resttemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

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
}
