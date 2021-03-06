package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins="*")
public class IngredientController {

	private IngredientRepository repo;

	@Autowired
	public IngredientController(IngredientRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public Flux<Ingredient> allIngredients() {
		return repo.findAll();
	}
	
}
