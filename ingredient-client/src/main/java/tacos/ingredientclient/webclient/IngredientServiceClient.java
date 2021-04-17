package tacos.ingredientclient.webclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tacos.Ingredient;

@Component
public class IngredientServiceClient {

	private WebClient.Builder wcBuilder;
	
	public IngredientServiceClient(
			@LoadBalanced WebClient.Builder wcBuilder) {
		this.wcBuilder = wcBuilder;
	}
	
	public Mono<Ingredient> getIngredientById(String ingredientId) {
		return wcBuilder.build()
			.get()
			.uri("http://ingredient-service/ingredients/{id}", ingredientId)
			.retrieve().bodyToMono(Ingredient.class);
	}
}
