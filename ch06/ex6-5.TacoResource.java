// 리스트 6.5 도메인 데이터와 하이퍼링크 리스트를 갖는 타코 리소스

package tacos.web.api;

import java.util.Date;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;
import lombok.Getter;
import tacos.Ingredient;
import tacos.Taco;

public class TacoResource extends ResourceSupport {

	@Getter
	private final String name;
	
	@Getter
	private final Date createdAt;
	
	@Getter
	private final List<Ingredient> ingredients;
	
	public TacoResource(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = taco.getIngredients();
	}
}
