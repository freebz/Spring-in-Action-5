// 리스트 11.2 스프링 WebFlux 애플리케이션의 스프링 시큐리티 구성하기

package tacos.security.flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
	
}
