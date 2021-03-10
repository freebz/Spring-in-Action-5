// 리스트 5.9 OrderProps 클래스 변경하기

package tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
...

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
@Validated
public class OrderProps {

	@Min(value=5, message="must be between 5 and 25")
	@Max(value=25, message="must be between 5 and 25")
	private int pageSize = 20;
}
