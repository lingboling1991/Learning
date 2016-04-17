package java_basic.web_service;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class Client {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		String url = "http://localhost:8888/cal";
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		String content = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		content = requestGenerate(content);
		
		StringEntity entity = new StringEntity(new String(
				content.getBytes("UTF-8"), "ISO-8859-1"));

		post.setHeader("Content-Type", "text/xml; charset=UTF-8");
		post.setEntity(entity);

		client.execute(post);
	}

	protected static String requestGenerate(String content) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder
				.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:org=\"http://org.apache.servicemix.wsn.push\">");
		contentBuilder.append("<soapenv:Header/>");
		contentBuilder.append("<soapenv:Body>");
		contentBuilder
				.append("<org:notificationProcess xmlns:org=\"http://org.apache.servicemix.wsn.push\">");
		contentBuilder.append(content);
		contentBuilder.append("</org:notificationProcess>");
		contentBuilder.append("</soapenv:Body>");
		contentBuilder.append("</soapenv:Envelope>");
		return contentBuilder.toString();
	}
}
