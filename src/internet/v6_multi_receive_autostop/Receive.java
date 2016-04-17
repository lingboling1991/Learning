package internet.v6_multi_receive_autostop;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

public class Receive implements Runnable {

	@Override
	public void run() {
		Flag flag = Flag.getInstance();

		try {
			String fileName = "result.txt";

			float counter = 0;

			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");

			byte[] data = new byte[100];

			MulticastSocket multicastSocket = new MulticastSocket(7777);// 创建多播套接字并绑定到发送端口

			multicastSocket.joinGroup(inetAddress);// 多播套接字加入多播组
			multicastSocket.setReceiveBufferSize(100 * 1024 * 1024);

			long receive_start = 0;
			long receive_end;

			System.out.println("start listening");
			while (!flag.isStop()) {

				DatagramPacket datagramPacket = new DatagramPacket(data,
						data.length);// 创建一个用于接收数据的数据包
				multicastSocket.receive(datagramPacket);// 接收数据包

				String res = new String(data).trim();

				System.out.println(res);

				if (counter == 0) {
					receive_start = System.currentTimeMillis();
				}

				counter++;
			}
			System.out.println("end listening");
			receive_end = System.currentTimeMillis();
			long time_res = receive_end - receive_start;

			String total_packets = "  total_packets == " + counter;
			method1(fileName, total_packets);

			String total_time = "  total_time == " + time_res;
			method1(fileName, total_time);

			System.exit(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void method1(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			out.write(conent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
