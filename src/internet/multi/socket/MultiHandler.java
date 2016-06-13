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
			multicastSocket.joinGroup(inetAddress);// 多播套接字加入多播组
			ByteArrayInputStream bais;
			ObjectInputStream ois;
			while (true) {
				byte[] data = new byte[4096];
				bais = new ByteArrayInputStream(data);
				DatagramPacket datagramPacket = new DatagramPacket(data,
						data.length);// 创建一个用于接收数据的数据包
				multicastSocket.receive(datagramPacket);// 接收数据包
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
					.getByName(topicCode);// 根据主题名返回主题的IP地址
			DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length,
					inetAddress, port);// 这里的端口没有用，最终转发还是看流表
			MulticastSocket multicastSocket = new MulticastSocket();
			multicastSocket.send(datagramPacket);// 发送数据包

			multicastSocket.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}