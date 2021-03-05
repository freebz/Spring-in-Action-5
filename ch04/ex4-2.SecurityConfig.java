// 리스트 4.2 인메모리 사용자 스토어에 사용자 정의하기

...
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
