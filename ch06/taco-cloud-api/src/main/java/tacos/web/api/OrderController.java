package tacos.web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path="/orders",
                produces="application/json")
@CrossOrigin(origins="*")
public class OrderController {
	
	private OrderRepository repo;
	
	public OrderController(OrderRepository repo) {
			this.repo = repo;
	}

	@PutMapping("/{orderId}")
	public Order putOrder(@RequestBody Order order) {
		return repo.save(order);
	}
	
	@PatchMapping(path="/{orderId}", consumes="application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId,
			@RequestBody Order patch) {
		
		Order order = repo.findById(orderId).get();
		if (patch.getDeliveryName() != null) {
			order.setDeliveryName(patch.getDeliveryName());
		}
		if (patch.getDeliveryStreet() != null) {
			order.setDeliveryStreet(patch.getDeliveryStreet());
		}
		if (patch.getDeliveryCity() != null) {
			order.setDeliveryCity(patch.getDeliveryCity());
		}
		if (patch.getDeliveryState() != null) {
			order.setDeliveryState(patch.getDeliveryState());
		}
		if (patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if (patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if (patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		
		return repo.save(order);
	}
	
	@DeleteMapping("/{orderId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			repo.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {}
	}
}
