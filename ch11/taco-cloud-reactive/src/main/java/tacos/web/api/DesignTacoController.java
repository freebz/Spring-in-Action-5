package tacos.web.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.data.TacoRepository;

@Configuration
public class DesignTacoController {

	private TacoRepository tacoRepo;
	
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@Bean
	public RouterFunction<?> routerFunction() {
		return route(GET("/design/recent"), this::recents)
			.andRoute(POST("/design"), this::postTaco);
	}
	
	public Mono<ServerResponse> recents(ServerRequest request) {
		return ServerResponse.ok()
			.body(tacoRepo.findAll().take(12), Taco.class);
	}
	
	public Mono<ServerResponse> postTaco(ServerRequest request) {
		Mono<Taco> taco = request.bodyToMono(Taco.class);
		Mono<Taco> savedTaco = tacoRepo.save(taco.block());
		return ServerResponse
			.created(URI.create(
				"http://localhost:8080/design/taco/" +
				savedTaco.block().getId()))
			.body(savedTaco, Taco.class);
	}
}
