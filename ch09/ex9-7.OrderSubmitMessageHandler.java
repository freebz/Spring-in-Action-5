// 리스트 9.7 메시지 핸들러를 통해서 타코 클라우드 API에 주문을 POST하기

package tacos.email;

import java.util.Map;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler
										implements GenericHandler<Order> {
	private RestTemplate rest;
	private ApiProperties apiProps;
	
	public OrderSubmitMessageHandler(
			ApiProperties apiProps, RestTemplate rest) {
		this.apiProps = apiProps;
		this.rest = rest;
	}
	
	@Override
	public Object handle(Order order, Map<String, Object> headers) {
		rest.postForObject(apiProps.getUrl(), order, String.class);
		return null;
	}
}
