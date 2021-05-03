// 리스트 18.2 100개의 타코가 생성될 때마다 알림 전송하기

@Service
@ManagedResource
public class TacoCounter
		extends AbstractRepositoryEventListener<Taco>
		implements NotificationPublisherAware {
	
	private AtomicLong counter;
	private NotificationPublisher np;
	...
	
	@Override
	public void setNotificationPublisher(NotificationPublisher np) {
		this.np = np;
	}
	...
	
	@ManagedOperation
	public long increment(long delta) {
		long before = counter.get();
		long after = counter.addAndGet(delta);
		if ((after / 100) > (before / 100)) {
			Notification notification = new Notification(
				"taco.count", this,
				before, after + "th taco created!");
			np.sendNotification(notification);
		}
		
		return after;
	}
}
