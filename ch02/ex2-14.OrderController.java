// 리스트 2.14 제출된 Order의 유효성 검사하기

...
import javax.validation.Valid;
import org.springframework.validation.Errors;
...

@PostMapping
public String processOrder(@Valid Order order, Errors errors) {
	if (errors.hasErrors()) {
		return "orderForm";
	}
	
	log.info("Order submitted: " + order);
	return "redirect:/";
}
