// 리스트 9.6 통합 변환기를 사용해서 입력 이메일을 타코 주문(Order 객체)으로 변환하기

@Component
public class EmailToOrderTransformer
	extends AbstractMailMessageTransformer<Order> {
	
	@Override
	protected AbstractIntegrationMessageBuilder<Order>
						doTransform(Message mailMessage) throws Exception {
		Order tacoOrder = processPayload(mailMessage);
		return MessageBuilder.withPayload(tacoOrder);
	}

	...
}
