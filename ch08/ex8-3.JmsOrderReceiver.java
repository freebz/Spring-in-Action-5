// 리스트 8.3 변환된 Order 객체 수신하기

package tacos.kitchen.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import tacos.Order;

@Component
public class JmsOrderReceiver implements OrderReceiver {
	private JmsTemplate jms;
	
	@Autowired
	public JmsOrderReceiver(JmsTemplate jms) {
		this.jms = jms;
	}
	
	public Order receiveOrder() {
		return (Order) jms.receiveAndConvert("tacocloud.order.queue");
	}
}
