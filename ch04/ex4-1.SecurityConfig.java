// 리스트 4.1 스프링 시큐리티의 기본 구성 클래스

package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation
          .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation
          .web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web
          .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
          .configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		  .antMatchers("/design", "/orders")
		    .access("hasRole('ROLE_USER')")
		  .antMatchers("/", "/**").access("permitAll")
		.and()
		  .httpBasic();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user1")
				.password("{noop}password1")
				.authorities("ROLE_USER")
				.and()
				.withUser("user2")
				.password("{noop}password2")
				.authorities("ROLE_USER");
	}
}