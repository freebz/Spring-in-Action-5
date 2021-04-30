// 리스트 16.6 TacoMetrics는 타코 식자재에 맞춘 메트릭 등록

package tacos.tacos;

import java.util.List;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import tacos.Ingredient;
import tacos.Taco;

@Component
public class TacoMetrics extends AbstractRepositoryEventListener<Taco> {
	private MeterRegistry meterRegistry;
	public TacoMetrics(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}
	
	@Override
	protected void onAfterCreate(Taco taco) {
		List<Ingredient> ingredients = taco.getIngredients();
		for (Ingredient ingredient : ingredients) {
			meterRegistry.counter("tacocloud",
				"ingredient", ingredient.getId()).increment();
		}
	}
}
