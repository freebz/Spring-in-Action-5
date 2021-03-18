// 리스트 6.2 타코 디자인 API 요청을 처리하는 REST 사용 컨트롤러

package tacos.web.api;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="design",
                produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {
	private TacoRepository tacoRepo;
	
	@Autowired
	EntityLinks entityLinks;
	
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(
				0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(page).getContent();
	}
}
