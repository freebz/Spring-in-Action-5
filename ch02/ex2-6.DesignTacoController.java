// 리스트 2.6 @PostMapping을 사용해서 POST 요청 처리하기

...
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
...

public class DesignTacoController {
	...
	@PostMapping
	public String processDesign(Taco design) {
		// 이 지점에서 타코 디자인(선택된 식자재 내역)을 저장한다.
		// 이 작업은 3장에서 할 것이다.
		log.info("Processing design: " + design);
		
		return "redirect:/orders/current";
	}
}
