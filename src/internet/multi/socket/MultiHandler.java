package internet.multi.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;

public class MultiHandler {
	private int port;
	private String topicCode;

	public MultiHandler(int port, String topicCode) {
		this.port = port;
		this.topicCode = topicCode;
	}

	public Object v6Receive() {
		try {
			MulticastSocket multicastSocket = new MulticastSocket(port);
			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName(topicCode);
			multicastSocket.joinGroup(inetAddress);// �ಥ�׽��ּ���ಥ��
			ByteArrayInputStream bais;
			ObjectInputStream ois;
			while (true) {
				byte[] data = new byte[4096];
				bais = new ByteArrayInputStream(data);
				DatagramPacket datagramPacket = new DatagramPacket(data,
						data.length);// ����һ�����ڽ������ݵ����ݰ�
				multicastSocket.receive(datagramPacket);// �������ݰ�
				ois = new ObjectInputStream(bais);
				Object obj = ois.readObject();
				// Obj obj = (Obj) o;
				// System.out.println(obj.name + " " + obj.age);
				return obj;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void v6Send(Object obj) {
		try {
			// Obj obj = new Obj();
			ByteArrayOutputStream baos;
			ObjectOutputStream oos;
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);

			oos.writeObject(obj);
			byte[] msg = baos.toByteArray();
			Inet6Address inetAddress = (Inet6Address) Inet6Address
					.getByName(topicCode);// �������������������IP��ַ
			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length,
					inetAddress, port);// ����Ķ˿�û���ã�����ת�����ǿ�����
			MulticastSocket multicastSocket = new MulticastSocket();
			multicastSocket.send(datagramPacket);// �������ݰ�

			multicastSocket.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}