package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tacos.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/design", "/orders").hasAuthority("USER")
			.antMatchers("/**").permitAll();
	}
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
		auth
			.userDetailsService(new UserDetailsService() {
				@Override
				public UserDetails loadUserByUsername(String username)
												throws UsernameNotFoundException {
					User user = userRepo.findByUsername(username);
					if (user == null) {
						throw new UsernameNotFoundException(
								username + " not found");
					}
					return user.toUserDetails();
				}
			});
	}
}
