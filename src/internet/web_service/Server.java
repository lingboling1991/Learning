package internet.web_service;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8888/cal", new CalImpl());
		// 启动一个java内嵌的web容器来实现web service的发布
	}
}
