package java_basic.web_service;

import javax.jws.WebService;

@WebService
public interface Cal {
	public int add(int a, int b);

	public void print(String in);
}
