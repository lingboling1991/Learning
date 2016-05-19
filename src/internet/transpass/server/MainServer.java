/**
 * MainServer.java Nov 23, 2009
 * <p>
 * Copyright 2009 xwz, Inc. All rights reserved.
 */
package internet.transpass.server;

import internet.transpass.util.ConnectionClientInfo;
import internet.transpass.util.MyProtocol;
import internet.transpass.util.StringUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xwz
 * @version 1.0, Nov 23, 2009 11:04:50 PM
 */
public class MainServer {
	// ���пͻ����б�
	private static List<ConnectionClientInfo> allClients = new ArrayList<ConnectionClientInfo>();

	// ��ʼP2P��������(�������)
	private static void StartP2PServiveChanege() throws IOException {
		DatagramSocket ds = new DatagramSocket(1000);

		byte[] buf = new byte[1024];
		DatagramPacket p = new DatagramPacket(buf, 1024);

		boolean isEnd = false;

		while (!isEnd) {
			ds.receive(p);

			// ȡ����Ϣ
			String content = new String(p.getData(), 0, p.getLength());
			String ip = p.getAddress().getHostAddress();
			int port = p.getPort();

			// ������յ�������
			if (!content.startsWith(MyProtocol.HEART)) {
				System.out.println(ip + ":" + port + " >>>> " + content);
			}

			// ������Ʋ���,ί�и�����������
			if (content.startsWith(MyProtocol.LOGIN)) {
				dealLogin(ds, p, content);
			} else if (content.startsWith(MyProtocol.HEART)) {
				dealHeart(ds, p, content);
			} else if (content.startsWith(MyProtocol.WANT_TO_CONNECT)) {
				notifyPunchHole(ds, p, content);
			} else if (content.startsWith(MyProtocol.SUCCESS_HOLE_TO)) {
				notifyPunchHoleSuccess(ds, p, content);
			} else {
				dealOther(ds, p, content);
			}
		}

		ds.close();
	}

	// �����½���󣬼�˭����login��Ϣ��˭�ͻ������пͻ��˵���Ϣ
	private static void dealLogin(DatagramSocket ds, DatagramPacket p,
	                              String content) {
		ConnectionClientInfo c = new ConnectionClientInfo();

		String[] clientLogin = StringUtil.splitString(content,
				MyProtocol.SPLITOR);
		System.out.println("clientLogin " + clientLogin.length);
		c.setNickname(clientLogin[1]);
		c.setIp(p.getAddress().getHostAddress());
		c.setPort(p.getPort());
		allClients.add(c);

		// ��ȡ���пͻ���,���ӳ��ַ���
		String listStr = MyProtocol.LIST_ONLINE + MyProtocol.SPLITOR
				+ serialList();

		System.out.println(listStr);
		DatagramPacket p2 = new DatagramPacket(listStr.getBytes(),
				listStr.getBytes().length, p.getAddress(), p.getPort());

		try {
			ds.send(p2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ���б��������л�
	private static String serialList() {
		String str = "";

		for (ConnectionClientInfo cif : allClients) {
			String nickname = cif.getNickname();
			String ip = cif.getIp();
			int port = cif.getPort();
			String one = ip + "," + port + "," + nickname + MyProtocol.SPLITOR;

			str += one;
		}

		return str;
	}

	// ����������
	private static void dealHeart(DatagramSocket ds, DatagramPacket p,
	                              String content) {
	}

	// ֪ͨ�򶴣��ѷ����ߵ���Ϣ>>>��Ϣ���ᵽ�Ŀͻ���
	private static void notifyPunchHole(DatagramSocket ds, DatagramPacket p,
	                                    String content) throws IOException {
		String[] clientInfo = StringUtil.splitString(content,
				MyProtocol.SPLITOR);

		String ip = clientInfo[1];
		int port = Integer.parseInt(clientInfo[2]);
		String nickname = clientInfo[3];

		System.out.println(ip + port + nickname);

		String punchToIp = p.getAddress().getHostAddress();
		int punchToPort = p.getPort();

		String send = MyProtocol.PUNCH_HOLE_TO + MyProtocol.SPLITOR + punchToIp
				+ MyProtocol.SPLITOR + punchToPort;
		System.out.println(send);

		DatagramPacket p2 = new DatagramPacket(send.getBytes(),
				send.getBytes().length, InetAddress.getByName(ip), port);

		ds.send(p2);

	}

	// ֪ͨ�򶴳ɹ�
	private static void notifyPunchHoleSuccess(DatagramSocket ds,
	                                           DatagramPacket p, String content) throws IOException {

		String[] clientInfo = StringUtil.splitString(content,
				MyProtocol.SPLITOR);

		String ip = clientInfo[1];
		int port = Integer.parseInt(clientInfo[2]);

		String send = MyProtocol.CAN_P2P_TO + MyProtocol.SPLITOR
				+ p.getAddress().getHostAddress() + MyProtocol.SPLITOR
				+ p.getPort();

		DatagramPacket p2 = new DatagramPacket(send.getBytes(),
				send.getBytes().length, InetAddress.getByName(ip), port);

		ds.send(p2);

	}

	// ����Э��û�ж���������
	private static void dealOther(DatagramSocket ds, DatagramPacket p,
	                              String content) {
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Server------------");
		StartP2PServiveChanege();
	}
}
