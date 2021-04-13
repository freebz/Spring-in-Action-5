// 리스트 12.2 Order 클래스를 카산드라 tacoorders 테이블로 매핑하기

@Data
@Table("tacoorders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private UUID id = UUIDs.timeBased();
	
	private Date placedAt = new Date();
	
	@Column("user")
	private UserUDT user;
	
	@Column("tacos")
	private List<TacoUDT> tacos = new ArrayList<>();
	
	public void addDesign(TacoUDT design) {
		this.tacos.add(design);
	}
}
