package java_basic.web_service;

import javax.jws.WebService;

@WebService(endpointInterface = "java_basic.web_service.Cal")
public class CalImpl implements Cal {
	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public void print(String in) {
		System.out.println("我收到了：" + in);
	}
}
