// 리스트 9.5 이메일을 받아 주문으로 제출하기 위해 통합 플로우 정의하기

package tacos.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;

@Configuration
public class TacoOrderEmailIntegrationConfig {

	@Bean
	public IntegrationFlow tacoOrderEmailFlow(
		EmailProperties emailProps,
		EmailToOrderTransformer emailToOrderTransformer,
		OrderSubmitMessageHandler orderSubmitHandler) {
		
		return IntegrationFlows
			.from(Mail.imapInboundAdapter(emailProps.getImapUrl()),
				e -> e.poller(
					Pollers.fixedDelay(emailProps.getPollRate())))
			.transform(emailToOrderTransformer)
			.handle(orderSubmitHandler)
			.get();
	}
	
}
