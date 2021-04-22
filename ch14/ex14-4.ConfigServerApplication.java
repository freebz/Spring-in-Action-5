// 리스트 14.4 ConfigServerApplication 클래스 변경하기

...
import org.springframework.cloud.config.server.EnableConfigServer;
...
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
