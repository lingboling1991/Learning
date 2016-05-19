package java_basic.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class MinaTimeServer {

	private static final String v4IP = "10.109.253.27";
	private static final String v6IP = "FF01:0000:0000:0000:0001:2345:6789:abcd";
	// ��������˿�
	private static final int PORT = 7777;

	public static void main(String[] args) throws IOException {
		// ��������˼���߳�
		IoAcceptor acceptor = new NioDatagramAcceptor();
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		// ������־��¼��
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// ���ñ��������
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));
		// ָ��ҵ���߼�������
		acceptor.setHandler(new TimeServerHandler());
		// ���ö˿ں�
		SocketAddress v4Addr = new InetSocketAddress(
				Inet4Address.getByName(v4IP), PORT);
		SocketAddress v6Addr = new InetSocketAddress(
				Inet6Address.getByName(v6IP), PORT);

		acceptor.bind(v6Addr);
		// ���������߳�
		acceptor.bind();
	}
}