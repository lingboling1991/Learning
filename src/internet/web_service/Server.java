package internet.web_service;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8888/cal", new CalImpl());
		// ����һ��java��Ƕ��web������ʵ��web service�ķ���
	}
}
