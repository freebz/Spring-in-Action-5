// 리스트 4.19 DesignTacoController의 showDesignForm() 변경하기

...
import java.security.Principal;

import tacos.data.UserRepository;
import tacos.User;
...

public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	
	private TacoRepository tacoRepo;
	
	private UserRepository userRepo;
	
	@Autowired
	public DesignTacoController(
			IngredientRepository ingredientRepo,
			TacoRepository tacoRepo,
			UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
		this.userRepo = userRepo;
	}

	...
	
	@GetMapping
	public String showDesignForm(Model model, Principal principal) {
	
		...
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
				filterByType(ingredients, type));
		}
		
		//model.addAttribute("taco", new Taco());
		
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		
		return "design";
	}
	...	
}
