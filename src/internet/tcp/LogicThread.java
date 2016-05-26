package internet.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LogicThread extends Thread {
	Socket socket;
	InputStream is;
	OutputStream os;

	public LogicThread(Socket socket) {
		this.socket = socket;
		start(); // �����߳�
	}

	public void run() {
		byte[] b = new byte[1024];
		try {
			// ��ʼ����
			os = socket.getOutputStream();
			is = socket.getInputStream();
			for (int i = 0; i < 3; i++) {
				// ��ȡ����
				int n = is.read(b);
				// �߼�����
				byte[] response = logic(b, 0, n);
				// ��������
				os.write(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	private void close() {
		try {
			// �ر���������
			os.close();
			is.close();
			socket.close();
		} catch (Exception e) {

		}
	}

	private byte[] logic(byte[] b, int off, int len) {
		byte[] response = new byte[len];
		// ����Ч���ݿ���������response��
		System.arraycopy(b, 0, response, 0, len);
		return response;
	}
}
