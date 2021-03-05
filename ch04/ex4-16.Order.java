// 리스트 4.16 Order 클래스에 새로운 속성 추가하기

...
import javax.persistence.ManyToOne;
...
@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {

...	
	private Date placedAt;
	
	@ManyToOne
	private User user;

...	
}
