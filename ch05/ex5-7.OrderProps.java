// 리스트 5.7 pageSize 속성을 홀더 클래스로 추출하기

package tacos.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
public class OrderProps {

	private int pageSize = 20;
}
