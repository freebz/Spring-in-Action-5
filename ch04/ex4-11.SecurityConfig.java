// 리스트 4.11 스프링 표현식을 사용해서 인증 규칙 정의하기

@Override
protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests()
		.antMatchers("/design", "/orders")
		  .access("hasRole('ROLE_USER')")
		.antMatchers("/", "/**").access("permitAll");
}
