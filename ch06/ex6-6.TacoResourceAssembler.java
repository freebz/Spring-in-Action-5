// 리스트 6.6 타코 리소스를 구성하는 리소스 어셈블러

package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.Taco;

public class TacoResourceAssembler
		extends ResourceAssemblerSupport<Taco, TacoResource> {

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}
	
	@Override
	protected TacoResource instantiateResource(Taco taco) {
		return new TacoResource(taco);
	}
	
	@Override
	public TacoResource toResource(Taco taco) {
		return createResourceWithId(taco.getId(), taco);
	}
}
