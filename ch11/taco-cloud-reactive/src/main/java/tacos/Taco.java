package tacos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Taco {
	
	@Id
	private UUID id;
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	
	private Date createdAt;
	
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<IngredientUDT> ingredients;
}
