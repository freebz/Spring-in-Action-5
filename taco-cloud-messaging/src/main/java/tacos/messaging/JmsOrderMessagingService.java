package tacos.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
	
	private JmsTemplate jms;
	private Destination orderQueue;
	
	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms,
			Destination orderQueue) {
		this.jms = jms;
		this.orderQueue = orderQueue;
	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.send(new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createObjectMessage(order);
//			}
//		}
//		jms.send(session -> session.createObjectMessage(order));
//	}
	
	@Bean
	public Destination orderQueue() {
		return new ActiveMQQueue("tacocloud.order.queue");
	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.send(
//			orderQueue,
//			session -> session.createObjectMessage(order));	
//	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.send(
//			"tacocloud.order.queue",
//			session -> session.createObjectMessage(order));	
//	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.convertAndSend("tacocloud.order.queue", order);
//	}
	
//	@Bean
//	public MappingJackson2MessageConverter messageConverter() {
//		MappingJackson2MessageConverter messageConverter =
//				new MappingJackson2MessageConverter();
//		messageConverter.setTypeIdPropertyName("_typeId");
//		return messageConverter;
//	}
	
	@Bean
	public MappingJackson2MessageConverter messageConverter() {
		MappingJackson2MessageConverter messageConverter =
				new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("_typeId");
		
		Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
		typeIdMappings.put("order", Order.class);
		messageConverter.setTypeIdMappings(typeIdMappings);
		
		return messageConverter;
	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.send("tacocloud.order.queue",
//			session -> {
//				Message message = session.createObjectMessage(order);
//				message.setStringProperty("X_ORDER_SOURCE", "WEB");
//				return message;
//			});
//	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.convertAndSend("tacocloud.order.queue", order, new MessagePostProcessor() {
//			@Override
//			public Message postProcessMessage(Message message) throws JMSException {
//				message.setStringProperty("X_ORDER_SOURCE", "WEB");
//				return message;
//			}
//		});
//	}
	
//	@Override
//	public void sendOrder(Order order) {
//		jms.convertAndSend("tacocloud.order.queue", order,
//			message -> {
//				message.setStringProperty("X_ORDER_SOURCE", "WEB");
//				return message;
//			});
//	}
	
	@Override
	public void sendOrder(Order order) {
		jms.convertAndSend("tacocloud.order.queue", order,
			this::addOrderSource);
	}
	
	private Message addOrderSource(Message message) throws JMSException {
		message.setStringProperty("X_ORDER_SOURCE", "Web");
		return message;
	}
}
