// 리스트 2.13 제출된 Taco의 유효성 검사하기

...
import javax.validation.Valid;
import org.springframework.validation.Errors;
...

@PostMapping
public String processDesign(@Valid Taco design, Errors errors) {
	if (errors.hasErrors()) {
		return "design";
	}
	
	// 이 지점에서 타코 디자인(선택된 식자재 내역)을 저장한다.
	// 이 작업은 3장에서 할 것이다.
	log.info("Processing design: " + design);
	
	return "redirect:/orders/current";
}
