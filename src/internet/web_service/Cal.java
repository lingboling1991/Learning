package internet.web_service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Cal {
	public int add(int a, int b);

	public void print(@WebParam(name = "xx") String in);
}
