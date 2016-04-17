package java_basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class MulThreadSocketServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		// 监听端口号
		int port = 7777;
		try {
			// 建立连接
			serverSocket = new ServerSocket(port);
			System.out.println("服务器已启动：");
			while (true) {
				// 获得连接
				socket = serverSocket.accept();
				String s = socket.getInetAddress().toString();
				String s2 = s.replace("/", "");
				System.out.println(s2);
				// 启动线程
				new LogicThread(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				serverSocket.close();
			} catch (Exception e) {

			}
		}
	}
}
