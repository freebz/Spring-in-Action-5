// 리스트 2.10 타코 주문 정보를 갖는 도메인 객체

package tacos;

import lombok.Data;

@Data
public class Order {

	private String deliveryName;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryState;
	private String deliveryZip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
}
