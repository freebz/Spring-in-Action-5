// 리스트 13.4 ServiceRegistryApplication 클래스 변경하기

...
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
...
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
