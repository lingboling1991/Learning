package internet.multi.autostop.send;

import internet.multi.autostop.send.Flag;

import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

public class Send implements Runnable {

	@Override
	public void run() {
		Flag flag = Flag.getInstance();

		try {
			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");// 根据主机名返回主机的IP地址

			byte[] msg = "ssssssssssssfffffffffdddddddddddddddddddddddddddddddddddddddddddddddddddddddffsddddddddddddd"
					.getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length,
					inetAddress, 7777);// 数据包包含消息内容，消息长度，多播IP和端口
			MulticastSocket multicastSocket = new MulticastSocket();
			multicastSocket.joinGroup(inetAddress);
			multicastSocket.setSendBufferSize(100 * 1024 * 1024);

			System.out.println("start sending");
			while (!flag.isStop()) {
				System.out.println("aaa");
				multicastSocket.send(datagramPacket);// 发送数据
			}
			System.out.println("end sending");
//			System.exit(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
