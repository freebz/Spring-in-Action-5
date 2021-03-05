// 리스트 4.12 커스텀 로그인 페이지 경로 지정하기

@Override
protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests()
		.antMatchers("/design", "/orders")
		.access("hasRole('ROLE_USER')")
		.antMatchers("/", "/**").access("permitAll")
		.and()
		.formLogin()
		.loginPage("/login");
}
