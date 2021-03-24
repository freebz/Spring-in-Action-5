// 리스트 8.4 주문 데이터를 리스닝하는 OrderListener 컴포넌트

package tacos.kitchen.messaging.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
	private KitchenUI ui;
	
	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}
	
	@JmsListener(destination = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		ui.displayOrder(order);
	}
}
