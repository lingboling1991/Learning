package internet.multi.socket;

import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

public class Receive_str_multi {

	public static void main(String[] args) {

		try {
			int num = 100000;
			if (args.length > 0)
				num = Integer.parseInt(args[0]);
			// String localAddr = Inet6Address.getLocalHost().getHostAddress();
			// System.out.println(localAddr);
			// if(args.length > 1)
			// localAddr = args[1];
			// float num = 100000;
			float counter = 0;

			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");

			byte[] data = new byte[100];

			// MulticastSocket multicastSocket = new MulticastSocket(new
			// InetSocketAddress(localAddr,7777));// 创建多播套接字并绑定到发送端口
			MulticastSocket multicastSocket = new MulticastSocket(7777);// 创建多播套接字并绑定到发送端口

			multicastSocket.joinGroup(inetAddress);// 多播套接字加入多播组
			multicastSocket.setReceiveBufferSize(100 * 1024 * 1024);

			long receive_start = 0;
			long receive_end;

			System.out.println("start listening");
			while (true) {
				DatagramPacket datagramPacket = new DatagramPacket(data,
						data.length);// 创建一个用于接收数据的数据包
				multicastSocket.receive(datagramPacket);// 接收数据包

				if (counter == 0) {
					receive_start = System.currentTimeMillis();
				}

				counter++;
				String res = new String(data).trim();

				if (res.contains("!")) {
					receive_end = System.currentTimeMillis();
					
					long time_res = receive_end - receive_start;

					System.out.println("drop_packets == " + (num - counter));
					System.out.println("total_time == " + time_res + ", drop_ratio == "
							+ ((num - counter) / num) * 100 + "%");
					
					// break;
				} else {
					if (counter % 1000 == 0) {
						System.out.println("counter == " + counter);
					}
				}
			}

			// long time_res = receive_end - receive_start;
			//
			// System.out.println("drop_packets == " + (num - counter));
			// System.out.println("total_time == " + time_res +
			// ", drop_ratio == "
			// + ((num - counter) / num) * 100 + "%");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
