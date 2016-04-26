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
		OutputStream os = conn.getOutputStream();// �õ������

		os.write(dataArray);
		os.flush();
		os.close();

		InputStream is = conn.getInputStream();// �õ�������
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();

		br.close();
		isr.close();
		is.close();
	}

	public static int zeroOne(int[] values, int[] weight, int capicity) {
		int total = values.length;
		int[][] tab = new int[total][capicity + 1];// tab[���Ե��ڼ�����Ʒ�ˣ����������ı��][����ʣ�����0-15]

		x = 0;

		// ��ʼ����ʣ�������0����Ʒ�������������������ݻ����������Χ��İ�����Ʒ�ܼ�ֵ�����ڵ�һ����Ʒ�ļ�ֵ
		// ����һ��һ������д��
		for (int i = weight[0]; i <= capicity; i++) {
			tab[total - 1][i] = values[0];
			combo[total - 1][i] += weight[0] * Math.pow(10, x);
		}
		x++;

		// ��1����Ʒ��ʼ������ÿһ����Ʒ
		for (int i = 1; i < total; i++) {
			// ÿ����Ʒ�������������������������ݻ�������Щ�������װ��ȥ�û��ǲ�װ��ȥ��
			for (int j = weight[i]; j <= capicity; j++) {
				if (values[i] + tab[total - i][j - weight[i]] > tab[total - i][j]) {// װ��ȥ��
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