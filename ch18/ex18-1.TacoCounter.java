// 리스트 18.1 생성된 타코의 수량을 세는 MBean

package tacos.tacos;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import tacos.Taco;
import tacos.data.TacoRepository;

@Service
@ManagedResource
public class TacoCounter
		extends AbstractRepositoryEventListener<Taco> {
	private AtomicLong counter;
	
	public TacoCounter(TacoRepository tacoRepo) {
		long initialCount = tacoRepo.count();
		this.counter = new AtomicLong(initialCount);
	}
	
	@Override
	protected void onAfterCreate(Taco entity) {
		counter.incrementAndGet();
	}
	
	@ManagedAttribute
	public long getTacoCount() {
		return counter.get();
	}
	
	@ManagedOperation
	public long increment(long delta) {
		return counter.addAndGet(delta);
	}
}
