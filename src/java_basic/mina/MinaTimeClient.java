package java_basic.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.*;
import java.nio.charset.Charset;

public class MinaTimeClient {

	private static final String v4IP = "10.109.253.27";
	private static final String v6IP = "FF01:0000:0000:0000:0001:2345:6789:abcd";
	private static final int PORT = 7777;

	public static void main(String[] args) throws UnknownHostException {
		// �����ͻ���������.
		NioDatagramConnector connector = new NioDatagramConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		// �������ӳ�ʱ���ʱ��
		connector.setConnectTimeoutCheckInterval(30);
		connector.getSessionConfig().setSendBufferSize(102400000);
		connector.setHandler(new TimeClientHandler());

		// ��������
		ConnectFuture cf = null;
		SocketAddress v4Addr = new InetSocketAddress(
				Inet4Address.getByName(v4IP), PORT);
		SocketAddress v6Addr = new InetSocketAddress(
				Inet6Address.getByName(v6IP), PORT);

		cf = connector.connect(v6Addr);
		// �ȴ����Ӵ������
		cf.awaitUninterruptibly();

		for (int i = 0; i < 10; i++) {
			cf.getSession().write("Hi Server!");
		}
		cf.getSession().write("quit");

		// �ȴ����ӶϿ�
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		// �ͷ�����
		connector.dispose();
	}
}