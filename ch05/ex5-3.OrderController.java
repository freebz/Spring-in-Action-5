// 리스트 5.3 ordersForUser() 메서드 변경하기

...
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
...
@GetMapping
public String ordersForUser(
		@AuthenticationPrincipal User user, Model model) {
	
	Pageable pageable = PageRequest.of(0, 20);
	model.addAttribute("orders",
		orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
	
	return "orderList";
}
