package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private Date placedAt = new Date();
	
	@Field("customer")
	private User user;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
}
