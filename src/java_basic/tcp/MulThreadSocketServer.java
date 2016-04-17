package java_basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class MulThreadSocketServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		// �����˿ں�
		int port = 7777;
		try {
			// ��������
			serverSocket = new ServerSocket(port);
			System.out.println("��������������");
			while (true) {
				// �������
				socket = serverSocket.accept();
				String s = socket.getInetAddress().toString();
				String s2 = s.replace("/", "");
				System.out.println(s2);
				// �����߳�
				new LogicThread(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�����
				serverSocket.close();
			} catch (Exception e) {

			}
		}
	}
}
