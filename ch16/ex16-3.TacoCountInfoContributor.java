// 리스트 16.3 InfoContributor의 커스텀 구현 클래스

package tacos.tacos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import tacos.data.TacoRepository;

@Component
public class TacoCountInfoContributor implements InfoContributor {
	private TacoRepository tacoRepo;
	
	public TacoCountInfoContributor(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@Override
	public void contribute(Builder builder) {
		long tacoCount = tacoRepo.count();
		Map<String, Object> tacoMap = new HashMap<String, Object>();
		tacoMap.put("count", tacoCount);
		builder.withDetail("taco-stats", tacoMap);
	}
}
