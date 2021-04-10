package tacos.web.api;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;

public class TacoCloudClient {

	public void getIngredient(String ingredientId) {
		Mono<Ingredient> ingredient = WebClient.create()
			.get()
			.uri("http://localhost:8080/ingredients/{id}", ingredientId)
			.retrieve()
			.bodyToMono(Ingredient.class);
		
		ingredient.subscribe(i -> { /*...*/ });
	}
	
	public void getIngredients() {
		Flux<Ingredient> ingredients = WebClient.create()
			.get()
			.uri("http://localhost:8080/ingredients")
			.retrieve()
			.bodyToFlux(Ingredient.class);
			
		ingredients.subscribe(i -> { /*...*/ });
	}
	
	@Bean
	public WebClient webClient() {
		return WebClient.create("http://localhost:8080");
	}
	
	@Autowired
	WebClient webClient;
	public Mono<Ingredient> getIngredientById(String ingredientId) {
		Mono<Ingredient> ingredient = webClient
			.get()
			.uri("/ingredients/{id}", ingredientId)
			.retrieve()
			.bodyToMono(Ingredient.class);
		ingredient.subscribe(i -> { /*...*/ });
		return ingredient;
	}
	
	public void getIngredientsWithTimeout() {
		Flux<Ingredient> ingredients = WebClient.create()
			.get()
			.uri("http://localhost:8080/ingredients")
			.retrieve()
			.bodyToFlux(Ingredient.class);
		
		ingredients
			.timeout(Duration.ofSeconds(1))
			.subscribe(
				i -> { /*...*/ },
				e -> {
					// handle timeout error
				});
	}
	
	public void postIngredient(Ingredient ingredient) {
		Mono<Ingredient> ingredientMono = Mono.just(ingredient);
		Mono<Ingredient> result = webClient
			.post()
			.uri("/ingredients")
			.body(ingredientMono, Ingredient.class)
			.retrieve()
			.bodyToMono(Ingredient.class);
		
		result.subscribe(i -> { /*...*/ });
	}
	
	public void syncIngredient(Ingredient ingredient) {
		Mono<Ingredient> result = webClient
			.post()
			.uri("/ingredients")
			.syncBody(ingredient)
			.retrieve()
			.bodyToMono(Ingredient.class);
		result.subscribe(i -> { /*...*/ });
	}
	
	public void putIngredient(Ingredient ingredient) {
		Mono<Void> result = webClient
			.put()
			.uri("/ingredients/{id}", ingredient.getId())
			.syncBody(ingredient)
			.retrieve()
			.bodyToMono(Void.class);
		result.subscribe();
	}
	
	public void deleteIngredient(String ingredientId) {
		Mono<Void> result = webClient
			.delete()
			.uri("/ingredients/{id}", ingredientId)
			.retrieve()
			.bodyToMono(Void.class);
		result.subscribe();
	}
	
	public void getIngredientWithError(String ingredientId) {
		Mono<Ingredient> ingredientMono = webClient
			.get()
			.uri("http://localhost:8080/ingredients/{id}", ingredientId)
			.retrieve()
			.bodyToMono(Ingredient.class);
		
		ingredientMono.subscribe(
			ingredient -> {
				// 식자재 데이터를 처리한다.
				// ...
			},
			error -> {
				// 에러를 처리한다.
				// ...
			});
	}
	
	public void getIngredientOnStatusUsingMethodReference(String ingredientId) {
		Mono<Ingredient> ingredientMono = webClient
			.get()
			.uri("http://localhost:8080/ingredients/{id}", ingredientId)
			.retrieve()
			.onStatus(HttpStatus::is4xxClientError,
					response -> Mono.just(new UnknownIngredientException()))
			.bodyToMono(Ingredient.class);
	}
	
	public void getIngredientOnStatusUsingLambda(String ingredientId) {
		Mono<Ingredient> ingredientMono = webClient
			.get()
			.uri("http://localhost:8080/ingredients/{id}", ingredientId)
			.retrieve()
			.onStatus(status -> status == HttpStatus.NOT_FOUND,
					response -> Mono.just(new UnknownIngredientException()))
			.bodyToMono(Ingredient.class);
	}
	
	public void getIngredientExchange(String ingredientId) {
		Mono<Ingredient> ingredientMono = webClient
			.get()
			.uri("http://localhost:8080/ingredients/{id}", ingredientId)
			.exchange()
			.flatMap(cr -> cr.bodyToMono(Ingredient.class));
	}
	
	public void getIngredientExchangeCheckHeader(String ingredientId) {
		Mono<Ingredient> ingredientMono = webClient
			.get()
			.uri("http://localhost:8080/ingredients/{id}", ingredientId)
			.exchange()
			.flatMap(cr -> {
				if (cr.headers().header("X_UNAVAILABLE").contains("true")) {
					return Mono.empty();
				}
				return Mono.just(cr);
			})
			.flatMap(cr -> cr.bodyToMono(Ingredient.class));
	}
	
	public static class UnknownIngredientException extends Exception {
		
	}
}
