// 리스트 4.4 사용자 정보 쿼리의 커스터마이징

@Override
public void configure(AuthenticationManagerBuilder auth)
		throws Exception {
	auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(
			"select username, password, enabled from users " +
			"where username=?")
		.authoritiesByUsernameQuery(
			"select username, authority from authorities " +
			"where username=?");
}
