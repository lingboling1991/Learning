/**
 * MainClient.java Nov 23, 2009
 * <p>
 * Copyright 2009 xwz, Inc. All rights reserved.
 */
package internet.transpass.client;

import internet.transpass.util.MyProtocol;
import internet.transpass.util.StringUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author xwz
 * @version 1.0, Nov 23, 2009 11:24:47 PM
 */
public class MainClient {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Client------------");

		Scanner scanner = new Scanner(System.in);

		System.out.print("nickname:");
		String nickname = scanner.next();// �ǳ�

		System.out.print("Server IP:");
		String ip = scanner.next();// ������ip��ַ

		System.out.print("Server port:");
		int port = scanner.nextInt();// �������˿�

		System.out.println(ip + "|" + port + "|" + nickname);

		// UDP
		DatagramSocket ds = new DatagramSocket();

		// ��½������
		String loginStr = MyProtocol.LOGIN + MyProtocol.SPLITOR + nickname;
		// ����Ҫ���͵İ�
		DatagramPacket lp = new DatagramPacket(loginStr.getBytes(),
				loginStr.length(), InetAddress.getByName(ip), port);
		// ���͵�½��Ϣ
		ds.send(lp);

		// ���������߳�
		String heartStr = MyProtocol.HEART + MyProtocol.SPLITOR + nickname
				+ "'s Heart Package";
		DatagramPacket hp = new DatagramPacket(heartStr.getBytes(),
				heartStr.length(), InetAddress.getByName(ip), port);
		new Thread(new HeartThread(ds, hp)).start();

		// ѭ������
		byte[] buf = new byte[1024];
		DatagramPacket rp = new DatagramPacket(buf, 1024);
		boolean isEnd = false;
		while (!isEnd) {
			ds.receive(rp);
			// ȡ����Ϣ
			String content = new String(rp.getData(), 0, rp.getLength());
			String rip = rp.getAddress().getHostAddress();
			int rport = rp.getPort();
			// ������յ�������
			System.out.println(rip + ":" + rport + " >>>> " + content);

			// ������Ʋ���,ί�и�����������
			if (content.startsWith(MyProtocol.LIST_ONLINE)) {
				dealListOnline(ds, rp, content);
			} else if (content.startsWith(MyProtocol.PUNCH_HOLE_TO)) {
				dealPunchTo(ds, rp, content);
			} else if (content.startsWith(MyProtocol.CAN_P2P_TO)) {
				firtTimeConnectP2P(ds, rp, content);
			} else if (content.startsWith(MyProtocol.HELLO_P2P_FRIEND)) {
				// ������Ҫ����������Ϣ�����Ǵ򶴻������
				String[] clientInfo = StringUtil.splitString(content,
						MyProtocol.SPLITOR);
				System.out.println(clientInfo[1]);
			} else if (content.startsWith(MyProtocol.P2P_MESSAGE)) {
			} else {
			}
		}
		ds.close();
	}

	// ��������֪ͨ,�Է��򶴳ɹ�
	private static void firtTimeConnectP2P(DatagramSocket ds,
	                                       DatagramPacket rp, String content) throws IOException {
		String[] clientInfo = StringUtil.splitString(content,
				MyProtocol.SPLITOR);

		String ip = clientInfo[1];
		int port = Integer.parseInt(clientInfo[2]);

		String send = MyProtocol.HELLO_P2P_FRIEND + MyProtocol.SPLITOR + "���";
		DatagramPacket p2 = new DatagramPacket(send.getBytes(),
				send.getBytes().length, InetAddress.getByName(ip), port);

		ds.send(p2);
	}

	// ��
	private static void dealPunchTo(DatagramSocket ds, DatagramPacket rp,
	                                String content) throws IOException {
		String[] clientInfo = StringUtil.splitString(content,
				MyProtocol.SPLITOR);

		String ip = clientInfo[1];
		int port = Integer.parseInt(clientInfo[2]);

		String send = MyProtocol.HELLO_P2P_FRIEND + MyProtocol.SPLITOR + "��";
		DatagramPacket p2 = new DatagramPacket(send.getBytes(),
				send.getBytes().length, InetAddress.getByName(ip), port);

		// ������,�෢����
		ds.send(p2);
		ds.send(p2);
		ds.send(p2);

		// ���߷������򶴳ɹ�
		send = MyProtocol.SUCCESS_HOLE_TO + MyProtocol.SPLITOR + ip
				+ MyProtocol.SPLITOR + port;
		DatagramPacket p3 = new DatagramPacket(send.getBytes(),
				send.getBytes().length, rp.getAddress(), rp.getPort());
		ds.send(p3);

	}

	// �����б��ʱ��
	private static void dealListOnline(DatagramSocket ds, DatagramPacket rp,
	                                   String content) throws IOException {
		//System.out.print("��˭����(192.168.0.2|1000|xwz),������(xxx������):");
		Scanner s = new Scanner(System.in);

		String input = s.next();

		if (input.equalsIgnoreCase("xxx")) {
			return;
		}

		input = MyProtocol.WANT_TO_CONNECT + MyProtocol.SPLITOR + input;
		DatagramPacket p = new DatagramPacket(input.getBytes(),
				input.getBytes().length, rp.getAddress(), rp.getPort());

		ds.send(p);
	}

}
