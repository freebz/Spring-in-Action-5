// 리스트 8.6 RabbitTemplate을 사용해서 RabbitMQ로부터 주문 데이터 가져오기

package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tacos.Order;

@Component
public class RabbitOrderReceiver {
	private RabbitTemplate rabbit;
	private MessageConverter converter;
	
	@Autowired
	public RabbitOrderReceiver(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
		this.converter = rabbit.getMessageConverter();
	}
	
	public Order receiveOrder() {
		Message message = rabbit.receive("tacocloud.orders");
		return message != null
				? (Order) converter.fromMessage(message)
				: null;
	}
}
