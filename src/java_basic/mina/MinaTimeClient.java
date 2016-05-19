package java_basic.mina;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class MinaTimeClient {

	private static final String v4IP = "10.109.253.27";
	private static final String v6IP = "FF01:0000:0000:0000:0001:2345:6789:abcd";
	private static final int PORT = 7777;

	public static void main(String[] args) throws UnknownHostException {
		// 创建客户端连接器.
		NioDatagramConnector connector = new NioDatagramConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		// 设置连接超时检查时间
		connector.setConnectTimeoutCheckInterval(30);
		connector.getSessionConfig().setSendBufferSize(102400000);
		connector.setHandler(new TimeClientHandler());

		// 建立连接
		ConnectFuture cf = null;
		SocketAddress v4Addr = new InetSocketAddress(
				Inet4Address.getByName(v4IP), PORT);
		SocketAddress v6Addr = new InetSocketAddress(
				Inet6Address.getByName(v6IP), PORT);

		cf = connector.connect(v6Addr);
		// 等待连接创建完成
		cf.awaitUninterruptibly();

		for (int i = 0; i < 10; i++) {
			cf.getSession().write("Hi Server!");
		}
		cf.getSession().write("quit");

		// 等待连接断开
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		// 释放连接
		connector.dispose();
	}
}