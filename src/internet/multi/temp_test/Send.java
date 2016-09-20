package internet.multi.temp_test;

import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

/**
 * Created by lenovo on 2016-6-13.
 */
public class Send {
	public static void main(String[] args) {
		while (true) {

			send(7777, "FF01:0000:0000:0000:0001:2345:6789:abcd");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void send(int port, String topic) {//"FF01:0000:0000:0000:0001:2345:6789:abcd"
		try {
			MulticastSocket multicastSocket = new MulticastSocket();
			byte[] msg = "QQQQQQQQ".getBytes();
			Inet6Address inetAddress = (Inet6Address) Inet6Address.getByName(topic);
			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, inetAddress, port);
			multicastSocket.send(datagramPacket);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
