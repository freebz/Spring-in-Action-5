// 리스트 6.8 커스텀 링크를 스프링 데이터 REST 엔드포인트에 추가하기

@Bean
public ResourceProcessor<PagedResources<Resource<Taco>>>
tacoProcessor(EntityLinks links) {
	
	return new ResourceProcessor<PagedResources<Resource<Taco>>>() {
		@Override
		public PagedResources<Resource<Taco>> process(
								PagedResources<Resource<Taco>> resource) {
			
			resource.add(
				links.linkFor(Taco.class)
					 .slash("recent")
					 .withRel("recents"));
			return resource;
		}
	}
}
