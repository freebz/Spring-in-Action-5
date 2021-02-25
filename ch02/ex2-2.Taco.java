// 리스트 2.2 타코 디자인을 정의하는 도메인 객체

package tacos;

import java.util.List;
import lombok.Data;

@Data
public class Taco {
	private String name;
	private List<String> ingredients;
}
