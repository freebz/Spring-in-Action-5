package tacos.ingredientclient.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	private IngredientClient client;
	
	@Autowired
	public IngredientController(IngredientClient client) {
		this.client = client;
	}
	
	@GetMapping("/{id}")
	public String ingredientDetailPage(@PathVariable("id") String id,
											Model model) {
		model.addAttribute("ingredient", client.getIngredient(id));
		return "ingredientDetail";
	}
}
