package internet.udp_still;

import java.io.IOException;
import java.net.*;

public class Client {

	public static String serverIP = "118.229.254.12";//118.229.254.12
	public static int serverPort = 5000;
	public static String clientIP = "10.109.253.29";
	public static int clientPort = 6000;

	public static void main(String args[]) throws IOException {

		DatagramSocket ds = null;

		try {
			ds = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			System.out.println("Cannot open port!");
		}

		byte[] buf = "��client����".getBytes();
		InetAddress destination = null;
		try {
			destination = InetAddress.getByName(serverIP);
		} catch (UnknownHostException e) {
			System.out.println("Cannot open findhost!");
		}
		DatagramPacket dp = new DatagramPacket(buf, buf.length, destination,
				serverPort);

		ds.send(dp);
//		ds.close();

		buf = new byte[1024];
		dp = new DatagramPacket(buf, 0, buf.length);
		ds.receive(dp);
		String data = new String(dp.getData(), 0, dp.getLength());
		System.out.println("client�յ���" + data);
		System.out.println("��������ַ" + dp.getAddress().toString() + "!!!" + "�������˿�" + dp.getPort());


		ds.close();
	}
}
