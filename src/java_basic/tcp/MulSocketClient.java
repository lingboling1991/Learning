package java_basic.tcp;

import java.io.*;
import java.net.*;

public class MulSocketClient {
	public static void main(String[] args) {
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		// 服务器端IP地址
		String serverIP = "localhost";
		// 服务器端端口号
		int port = 7777;
		// 发送内容
		String data[] = { "First", "Second", "Third" };
		try {
			// 建立连接
			socket = new Socket(serverIP, port);
			// 初始化流
			os = socket.getOutputStream();
			is = socket.getInputStream();
			byte[] b = new byte[1024];
			for (int i = 0; i < data.length; i++) {
				// 发送数据
				os.write(data[i].getBytes());
				// 接收数据
				int n = is.read(b);
				// 输出反馈数据
				System.out.println("服务器反馈：" + new String(b, 0, n));
			}
		} catch (Exception e) {
			e.printStackTrace(); // 打印异常信息
		} finally {
			try {
				// 关闭流和连接
				is.close();
				os.close();
				socket.close();
			} catch (Exception e2) {

			}
		}
	}
}
