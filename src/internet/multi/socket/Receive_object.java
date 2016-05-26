package internet.multi.socket;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class Receive_object {
	private static byte[] buf = new byte[4096];
	private static ByteArrayInputStream bais;
	private static ObjectInputStream ois;

	public static void main(String[] args) {
		try {
			// Inet6Address inetAddress = (Inet6Address) Inet6Address
			// .getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");

			InetAddress inetAddress = InetAddress.getByName("230.0.10.100");
			int port = 7777;

			String localAddr = "10.109.253.27";
			MulticastSocket multicastSocket = new MulticastSocket(new InetSocketAddress(localAddr, port));// �����ಥ�׽��ֲ��󶨵����Ͷ˿�

			multicastSocket.joinGroup(inetAddress);// �ಥ�׽��ּ���ಥ��
			multicastSocket.setLoopbackMode(true);
			multicastSocket.setReceiveBufferSize(10 * 1024 * 1024);

			DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);// ����һ�����ڽ������ݵ����ݰ�

			System.out.println("ready to receive ...");
			while (true) {
				bais = new ByteArrayInputStream(buf);
				multicastSocket.receive(datagramPacket);// �������ݰ�
				ois = new ObjectInputStream(bais);
				Object msg = ois.readObject();
				String string = (String) msg;
				System.out.println(string);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
