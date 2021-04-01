package tacos.kitchen.messaging.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.kitchen.KitchenUI;

@Slf4j
@Component
public class OrderListener {

	private KitchenUI ui;
	
	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}
	
//	@KafkaListener(topics="tacocloud.orders.topic")
//	public void handle(Order order) {
//		ui.displayOrder(order);
//	}
	
//	@KafkaListener(topics="tacocloud.orders.topic")
//	public void handle(Order order, ConsumerRecord<Order> record) {
//		log.info("Received from partition {} with timestamp {}",
//			record.partition(), record.timestamp());
//		ui.displayOrder(order);
//	}
	
	@KafkaListener(topics="tacocloud.orders.topic")
	public void handle(Order order, Message<Order> message) {
		MessageHeaders headers = message.getHeaders();
		
		log.info("Received from partition {} with timestamp {}",
			headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
			headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
		ui.displayOrder(order);
	}
}
