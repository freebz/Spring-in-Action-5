// 리스트 4.3 JDBC 기반의 사용자 스토어로 인증하기

...
import javax.sql.DataSource;
...

@Autowired
DataSource dataSource;
	
@Override
public void configure(AuthenticationManagerBuilder auth)
		throws Exception {
		auth
		  .jdbcAuthentication()
		  .dataSource(dataSource);
}
