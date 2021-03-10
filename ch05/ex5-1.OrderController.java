// 리스트 5.1 OrderController에 ordersForUser() 메서드 추가하기

...	
@GetMapping
public String ordersForUser(
		@AuthenticationPrincipal User user, Model model) {
	model.addAttribute("orders",
			orderRepo.findByUserOrderByPlacedAtDesc(user));
	
	return "orderList";
}
