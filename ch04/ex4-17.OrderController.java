// 리스트 4.17 OrderController의 processOrder() 변경하기

...
import org.springframework.security.core.annotation.AuthenticationPrincipal;
...
import tacos.User;
...	
@PostMapping
public String processOrder(@Valid Order order,
						 Errors errors, SessionStatus sessionStatus
						 , @AuthenticationPrincipal User user) {
	if (errors.hasErrors()) {
		return "orderForm";
	}
	
	order.setUser(user);
	
	orderRepo.save(order);
	sessionStatus.setComplete();
	
	return "redirect:/";
}
...
