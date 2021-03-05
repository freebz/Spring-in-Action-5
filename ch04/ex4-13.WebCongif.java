// 리스트 4.13 WebConfig에 뷰 컨트롤러 선언하기

...
@Override
public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/").setViewName("home");
	registry.addViewController("/login");
}
