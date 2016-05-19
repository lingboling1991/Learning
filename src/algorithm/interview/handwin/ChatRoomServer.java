package algorithm.interview.handwin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 网络多客户端聊天室
 * 功能1： 客户端通过Java NIO连接到服务端，支持多客户端的连接
 * 功能2：客户端初次连接时，服务端提示输入昵称，如果昵称已经有人使用，提示重新输入，如果昵称唯一，则登录成功，之后发送消息都需要按照规定格式带着昵称发送消息
 * 功能3：客户端登录后，发送已经设置好的欢迎信息和在线人数给客户端，并且通知其他客户端该客户端上线
 * 功能4：服务器收到已登录客户端输入内容，转发至其他登录客户端。
 * <p>
 * TODO 客户端下线检测
 */
public class ChatRoomServer {

	static final int port = 9999;
	private static final String USER_EXIST = "system message: user exist, please change a name";
	private static final String USER_CONTENT_SPILIT = "#@#";
	private static final String CLIENT_QUIT = "client_quit";
	public static HashMap<SelectionKey, Long> activeTimer;//用来记录在线人数，以及昵称
	private static HashSet<String> users = new HashSet<String>();//相当于自定义协议格式，与客户端协商好
	private static boolean flag = false;
	private Selector selector = null;
	private Charset charset = Charset.forName("UTF-8");

	// TODO 要是能检测下线，就不用这么统计了
	public static int OnlineNum(Selector selector) {
		int res = 0;
		for (SelectionKey key : selector.keys()) {
			Channel targetchannel = key.channel();

			if (targetchannel instanceof SocketChannel) {
				res++;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		new ChatRoomServer().init();
	}

	public void init() throws IOException {
		new Thread(new ActiveCheck()).start();

		selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(port));

		ssc.configureBlocking(false);//非阻塞的方式
		ssc.register(selector, SelectionKey.OP_ACCEPT);//注册到选择器上，设置为监听状态

		System.out.println("Server is listening now...");

		while (true) {
			int readyChannels = selector.select();
			if (readyChannels == 0)
				continue;
			Set selectedKeys = selector.selectedKeys();//可以通过这个方法，知道可用通道的集合
			Iterator keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey sk = (SelectionKey) keyIterator.next();
				keyIterator.remove();
				dealWithSelectionKey(ssc, sk);
			}
		}
	}

	public void dealWithSelectionKey(ServerSocketChannel server, SelectionKey sk)
			throws IOException {
		if (sk.isAcceptable()) {
			SocketChannel sc = server.accept();
			activeTimer.put(sk, System.currentTimeMillis());

			sc.configureBlocking(false);

			//注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
			sc.register(selector, SelectionKey.OP_READ);
			System.out.println("Server is listening from client :"
					+ sc.getRemoteAddress());
			sc.write(charset.encode("Please input your name."));
		}
		//处理来自客户端的数据读取请求
		else if (sk.isReadable()) {
			//返回该SelectionKey对应的 Channel，其中有数据需要读取
			activeTimer.put(sk, System.currentTimeMillis());
			SocketChannel sc = (SocketChannel) sk.channel();
			ByteBuffer buff = ByteBuffer.allocate(1024);
			StringBuilder content = new StringBuilder();
			try {
				while (sc.read(buff) > 0) {
					buff.flip();
					content.append(charset.decode(buff));
				}
				System.out.println("Server is listening from client "
						+ sc.getRemoteAddress() + " data rev is: " + content);
				//将此对应的channel设置为准备下一次接受数据
				sk.interestOps(SelectionKey.OP_READ);
			} catch (IOException io) {
				sk.cancel();
				if (sk.channel() != null) {
					sk.channel().close();
				}
			}
			if (content.length() > 0) {
				String[] arrayContent = content.toString().split(
						USER_CONTENT_SPILIT);
				//注册用户
				if (arrayContent.length == 1) {
					String name = arrayContent[0];
					if (users.contains(name)) {
						sc.write(charset.encode(USER_EXIST));
					} else {
						users.add(name);
						int num = OnlineNum(selector);
						String message = "welcome " + name
								+ " to chat room! Online numbers:" + num;
						BroadCast(selector, null, message);
					}
				}
				//注册完了，发送消息
				else if (arrayContent.length > 1) {
					String name = arrayContent[0];
					String message = content.substring(name.length()
							+ USER_CONTENT_SPILIT.length());
					message = name + " say " + message;
					if (users.contains(name)) {
						//不回发给发送此内容的客户端
						BroadCast(selector, sc, message);
					}
				}
			}
		}
	}

	public void BroadCast(Selector selector, SocketChannel except,
	                      String content) throws IOException {
		//广播数据到所有的SocketChannel中
		for (SelectionKey key : selector.keys()) {
			Channel targetchannel = key.channel();
			//如果except不为空，不回发给发送此内容的客户端
			if (targetchannel instanceof SocketChannel
					&& targetchannel != except) {
				SocketChannel dest = (SocketChannel) targetchannel;
				dest.write(charset.encode(content));
			}
		}
	}

	private class ActiveCheck implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (SelectionKey tmpSk : activeTimer.keySet()) {
				long res = System.currentTimeMillis() - activeTimer.get(tmpSk);
				if (res > 60 * 1000) {
					SocketChannel tmpSc = (SocketChannel) tmpSk.channel();
					activeTimer.remove(tmpSk);
					try {
						sendQuitMsg(tmpSk);
						// BroadCast(selector, tmpSc, );
						tmpSk.cancel();
						tmpSc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private void sendQuitMsg(SelectionKey tmpSk) throws IOException {
			SocketChannel sc = (SocketChannel) tmpSk.channel();
			String line = CLIENT_QUIT;
			sc.write(charset.encode(line));
		}

	}
}