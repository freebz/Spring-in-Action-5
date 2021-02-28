// 리스트 3.3 ID와 타임스탬프 필드를 Taco 클래스에 추가하기

...
import java.util.Date;

@Data
public class Taco {
	private Long id;
	private Date createdAt;
	...
}
