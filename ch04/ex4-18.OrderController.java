// 리스트 4.18 OrderController의 orderForm() 변경하기

...
import org.springframework.web.bind.annotation.ModelAttribute;
...
@GetMapping("/current")
public String orderForm(@AuthenticationPrincipal User user,
						   @ModelAttribute Order order) {
	if (order.getDeliveryName() == null) {
		order.setDeliveryName(user.getFullname());
	}
	if (order.getDeliveryStreet() == null) {
		order.setDeliveryStreet(user.getStreet());
	}
	if (order.getDeliveryCity() == null) {
		order.setDeliveryCity(user.getCity());
	}
	if (order.getDeliveryState() == null) {
		order.setDeliveryState(user.getState());
	}
	if (order.getDeliveryZip() == null) {
		order.setDeliveryZip(user.getZip());
	}
	
	return "orderForm";
}
