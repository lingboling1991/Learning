package sdn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
