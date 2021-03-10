// 리스트 5.8 OrderController 변경하기

...
//import org.springframework.boot.context.properties.ConfigurationProperties;
...
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
//@ConfigurationProperties(prefix="taco.orders")
public class OrderController {
	
//	private int pageSize = 20;
	
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
	private OrderProps props;
	
	private OrderRepository orderRepo;
	
	public OrderController(OrderRepository orderRepo,
			OrderProps props) {
		this.orderRepo = orderRepo;
		this.props = props;
	}

	...
	
	@GetMapping
	public String ordersForUser(
			@AuthenticationPrincipal User user, Model model) {
		
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders",
			orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
		
		return "orderList";
	}
	
	...
}
