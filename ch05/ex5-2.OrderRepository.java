// 리스트 5.2 findByUserOrderByPlacedAtDesc() 메서드 추가하기

...
import java.util.List;
import tacos.User;
...
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUserOrderByPlacedAtDesc(User user);
}
