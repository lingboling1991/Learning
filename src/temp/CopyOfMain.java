package temp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CopyOfMain {
	public static int[][] combo;
	public static int x;

	public static void main(String[] args) throws Exception {

		post("asdfg");
	}

	public static void post(String topics) throws Exception {
		String postData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
				+ "<soapenv:Body> <m:print  xmlns:m=\"http://web_service.java_basic/\"> <arg0>"
				+ topics
				+ "</arg0> </m:print> </soapenv:Body> </soapenv:Envelope>";
		byte[] dataArray = postData.getBytes("UTF-8");

		String spec = "http://localhost:8888/cal";
		URL url = new URL(spec);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestProperty("content-type", "text/xml;charset=UTF-8");

		conn.connect();
		OutputStream os = conn.getOutputStream();// 拿到输出流

		os.write(dataArray);
		os.flush();
		os.close();

		InputStream is = conn.getInputStream();// 拿到输入流
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();

		br.close();
		isr.close();
		is.close();
	}

	public static int zeroOne(int[] values, int[] weight, int capicity) {
		int total = values.length;
		int[][] tab = new int[total][capicity + 1];// tab[尝试到第几件物品了，这里是他的编号][包中剩余体积0-15]

		x = 0;

		// 初始化：剩余体积从0号物品的体积，到背包的最大容积，在这个范围里的包中商品总价值都等于第一件物品的价值
		// 倒着一行一行来填写的
		for (int i = weight[0]; i <= capicity; i++) {
			tab[total - 1][i] = values[0];
			combo[total - 1][i] += weight[0] * Math.pow(10, x);
		}
		x++;

		// 从1号物品开始，遍历每一件物品
		for (int i = 1; i < total; i++) {
			// 每件物品，从它的体积，到背包的最大容积，看这些情况下是装进去好还是不装进去好
			for (int j = weight[i]; j <= capicity; j++) {
				if (values[i] + tab[total - i][j - weight[i]] > tab[total - i][j]) {// 装进去好
					combo[total - 1 - i][j] += weight[i] * Math.pow(10, x);
				} else {
					combo[total - 1 - i][j] += combo[total - i][j];
				}
				tab[total - 1 - i][j] = Math.max(tab[total - i][j], values[i]
						+ tab[total - i][j - weight[i]]);
			}
			x += 1;
		}

		return tab[0][capicity];
	}

}