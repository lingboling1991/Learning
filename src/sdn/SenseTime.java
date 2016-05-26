package sdn;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class SenseTime {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		while (true) {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpGet = new HttpPost(
					"http://10.109.253.2:8080//wm/core/controller/switches/json");
			CloseableHttpResponse response = httpclient.execute(httpGet);
			System.out.println(response);
		}
	}
}
