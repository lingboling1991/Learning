package internet.web_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

public class Client {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		String content = "qwertyu";

		String sendString = requestGenerate(content);
		String sendString_2 = requestGenerate_2(content);// 这两个生成函数都可以

		String target = "http://localhost:8888/cal";
		URL url = new URL(target);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestProperty("content-type", "text/xml;charset=UTF-8");

		conn.connect();
		OutputStream os = conn.getOutputStream();// 拿到输出流
		os.write(sendString.getBytes("UTF-8"));
		os.flush();
		os.close();

		InputStream is = conn.getInputStream();// 拿到输入流，不拿输入流的话，就不能完成整个POST流程
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		System.out.println(s);

		br.close();
		isr.close();
		is.close();
	}

	protected static String requestGenerate(String content) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder
				.append("<soapenv:Envelope "
						+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
						+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
						+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
						+ "soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		contentBuilder.append("<soapenv:Body>");
		contentBuilder
				.append("<m:print xmlns:m=\"http://web_service.java_basic/\">");
		contentBuilder.append("<xx>");// 在Cal接口中定义的参数名字
		contentBuilder.append(content);
		contentBuilder.append("</xx>");
		contentBuilder.append("</m:print>");
		contentBuilder.append("</soapenv:Body>");
		contentBuilder.append("</soapenv:Envelope>");
		return contentBuilder.toString();
	}

	protected static String requestGenerate_2(String content) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("<soap:Envelope "
				+ "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		contentBuilder.append("<soap:Body>");
		contentBuilder
				.append("<m:print xmlns:m=\"http://web_service.java_basic/\">");
		contentBuilder.append("<xx>");// 在Cal接口中定义的参数名字
		contentBuilder.append(content);
		contentBuilder.append("</xx>");
		contentBuilder.append("</m:print>");
		contentBuilder.append("</soap:Body>");
		contentBuilder.append("</soap:Envelope>");
		return contentBuilder.toString();
	}
}
