// 리스트 8.7 RabbitMQ 메시지 리스너로 메서드를 선언하기

package tacos.kitchen.messaging.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
	private KitchenUI ui;
	
	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}
	
	@RabbitListener(queues = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		ui.displayOrder(order);
	}
}
