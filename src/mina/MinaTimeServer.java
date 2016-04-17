package mina;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class MinaTimeServer {

	private static final String v4IP = "10.109.253.27";
	private static final String v6IP = "FF01:0000:0000:0000:0001:2345:6789:abcd";
	// 定义监听端口
	private static final int PORT = 7777;

	public static void main(String[] args) throws IOException {
		// 创建服务端监控线程
		IoAcceptor acceptor = new NioDatagramAcceptor();
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		// 设置日志记录器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// 设置编码过滤器
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));
		// 指定业务逻辑处理器
		acceptor.setHandler(new TimeServerHandler());
		// 设置端口号
		SocketAddress v4Addr = new InetSocketAddress(
				Inet4Address.getByName(v4IP), PORT);
		SocketAddress v6Addr = new InetSocketAddress(
				Inet6Address.getByName(v6IP), PORT);

		acceptor.bind(v6Addr);
		// 启动监听线程
		acceptor.bind();
	}
}