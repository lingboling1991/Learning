package internet.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MulSocketClient {
	public static void main(String[] args) {
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		// ��������IP��ַ
		String serverIP = "localhost";
		// �������˶˿ں�
		int port = 7777;
		// ��������
		String data[] = {"First", "Second", "Third"};
		try {
			// ��������
			socket = new Socket(serverIP, port);
			// ��ʼ����
			os = socket.getOutputStream();
			is = socket.getInputStream();
			byte[] b = new byte[1024];
			for (int i = 0; i < data.length; i++) {
				// ��������
				os.write(data[i].getBytes());
				// ��������
				int n = is.read(b);
				// �����������
				System.out.println("������������" + new String(b, 0, n));
			}
		} catch (Exception e) {
			e.printStackTrace(); // ��ӡ�쳣��Ϣ
		} finally {
			try {
				// �ر���������
				is.close();
				os.close();
				socket.close();
			} catch (Exception e2) {

			}
		}
	}
}
