package tacos.security.flux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
	Mono<User> findByUsername(String username);
}
