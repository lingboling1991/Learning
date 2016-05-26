package internet.multi.autostop.send;

import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

public class Send implements Runnable {

	@Override
	public void run() {
		Flag flag = Flag.getInstance();

		try {
			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");// ��������������������IP��ַ

			byte[] msg = "ssssssssssssfffffffffdddddddddddddddddddddddddddddddddddddddddddddddddddddddffsddddddddddddd"
					.getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length,
					inetAddress, 7777);// ���ݰ�������Ϣ���ݣ���Ϣ���ȣ��ಥIP�Ͷ˿�
			MulticastSocket multicastSocket = new MulticastSocket();
			multicastSocket.joinGroup(inetAddress);
			multicastSocket.setSendBufferSize(100 * 1024 * 1024);

			System.out.println("start sending");
			while (!flag.isStop()) {
				System.out.println("aaa");
				multicastSocket.send(datagramPacket);// ��������
			}
			System.out.println("end sending");
//			System.exit(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
