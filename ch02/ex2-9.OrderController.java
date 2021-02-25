// 리스트 2.9 타코 주문 제출 처리하기
...
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
...

public class OrderController {
	...
	@PostMapping
	public String processOrder(Order order) {
		log.info("Order submitted: " + order));
		return "redirect:/";
	}
}
