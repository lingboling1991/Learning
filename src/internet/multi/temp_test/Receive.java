package internet.multi.temp_test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

/**
 * Created by lenovo on 2016-6-13.
 */
public class Receive {
	public static void main(String[] args) {
		while (true) {
			String res = receive(7777, "FF01:0000:0000:0000:0001:2345:6789:abcd");
			System.out.println(res);
		}
	}

	public static String receive(int port, String topic) {
		String res = null;
		try {
			Inet6Address inetAddress = (Inet6Address) Inet6Address.getByName(topic);
			byte[] data = new byte[100];

			MulticastSocket multicastSocket = new MulticastSocket(port);
			multicastSocket.joinGroup(inetAddress);
			DatagramPacket datagramPacket = new DatagramPacket(data, data.length);

			multicastSocket.receive(datagramPacket);
			res = new String(data).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
