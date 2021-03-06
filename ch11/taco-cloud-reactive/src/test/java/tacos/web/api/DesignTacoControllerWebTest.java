package tacos.web.api;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DesignTacoControllerWebTest {
	
	@Autowired
	private WebTestClient testClient;
	
	@Test
	public void shouldReturnRecentTacos() throws IOException {
		testClient.get().uri("/design/recent")
			.accept(MediaType.APPLICATION_JSON).exchange()
			.expectStatus().isOk()
			.expectBody()
				.jsonPath("$[?(@.id == 'TACO1')].name")
					.isEqualTo("Carnivore")
				.jsonPath("$[?(@.id == 'TACO2')].name")
					.isEqualTo("Bovine Bounty")
				.jsonPath("$[?(@.id == 'TACO3')].name")
					.isEqualTo("Veg-Out");
	}	
}
