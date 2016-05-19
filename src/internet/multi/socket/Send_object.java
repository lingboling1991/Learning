package internet.multi.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Send_object {

	public static void main(String[] args) throws IOException {

		// Inet6Address inetAddress = (Inet6Address) Inet6Address
		// .getByName("FF01:0000:0000:0000:0001:2345:6789:abcd");

		InetAddress inetAddress = InetAddress.getByName("230.0.10.100");
		int port = 7777;

		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		String obj = new String("1234567890");
		byte[] outHello = null;

		baos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(baos);

		oos.writeObject(obj);
		outHello = baos.toByteArray();

		MulticastSocket s = new MulticastSocket();
		s.joinGroup(inetAddress);

		DatagramPacket p = new DatagramPacket(outHello, outHello.length,
				inetAddress, port);
		s.send(p);
	}

}
