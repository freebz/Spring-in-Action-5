// 리스트 3.21 컨버터 변경하기

...
import java.util.Optional;
...
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	...
	
	@Override
	public Ingredient convert(String id) {
		Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
		return optionalIngredient.isPresent() ?
								optionalIngredient.get() : null;
	}
}
