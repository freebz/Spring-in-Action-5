// 리스트 5.5 OrderController에 구성 속성 추가하기

...
import org.springframework.boot.context.properties.ConfigurationProperties;
...

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private int pageSize = 20;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	...
		
	@GetMapping
	public String ordersForUser(
			@AuthenticationPrincipal User user, Model model) {
		
		Pageable pageable = PageRequest.of(0, pageSize);
		model.addAttribute("orders",
			orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
		
		return "orderList";
	}
}
