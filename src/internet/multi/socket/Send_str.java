package internet.multi.socket;

import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;
import java.net.Socket;

public class Send_str {
	private static long send_start;
	private static long send_end;
	private static long interval;
	private static long sum = 0;

	public static void main(String[] args) {
		try {
			int num = 100000;
			if (args.length > 0)
				num = Integer.parseInt(args[0]);
			// int sleep_time = Integer.parseInt(args[1]);
			// int num = 100000;

			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");

			byte[] msg = "ssssssssssssfffffffffdddddddddddddddddddddddddddddddddddddddddddddddddddddddffsddddddddddddd"
					.getBytes();

			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length,
					inetAddress, 7777);

			MulticastSocket multicastSocket = new MulticastSocket();
			Socket s = new Socket();
			multicastSocket.joinGroup(inetAddress);
			multicastSocket.setSendBufferSize(100 * 1024 * 1024);
			// multicastSocket.

			for (int i = 1; i <= num; i++) {
				if (i % 1000 == 0) {
					System.out.println("count = " + i);
				}

				if (i == 1) {
					send_start = System.currentTimeMillis();
				} else if (i == num) {
					send(multicastSocket);
					send_end = System.currentTimeMillis();
				}

				multicastSocket.send(datagramPacket);

				// int nullLoop = sleep_time;
				// while (nullLoop > 0) {
				// nullLoop--;
				// }
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		interval = send_end - send_start;
		sum += interval;
		System.out.println("total_time == " + interval);
	}

	public static void send(MulticastSocket multicastSocket) {
		try {
			byte[] msg = "aa!!".getBytes();

			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");

			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length,
					inetAddress, 7777);

			multicastSocket.send(datagramPacket);

			System.out.println("the fianl message is:" + new String(msg));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}