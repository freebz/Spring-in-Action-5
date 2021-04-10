package tacos.security.flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(
							ServerHttpSecurity http) {
		return http
			.authorizeExchange()
			.pathMatchers("/design", "/orders").hasAuthority("USER")
			.anyExchange().permitAll()
			.and()
			.build();
	}
	
	@Bean
	public ReactiveUserDetailsService userDetailsService(
													UserRepository userRepo) {
		return new ReactiveUserDetailsService() {
			@Override
			public Mono<UserDetails> findByUsername(String username) {
				return userRepo.findByUsername(username)
								.map(user -> {
									return user.toUserDetails();
								});
			}
		};
	}
}
