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
 * �����ͻ��������� ����1�� �ͻ���ͨ��Java NIO���ӵ�����ˣ�֧�ֶ�ͻ��˵�����
 * ����2���ͻ��˳�������ʱ���������ʾ�����ǳƣ�����ǳ��Ѿ�����ʹ�ã���ʾ�������룬����ǳ�Ψһ�����¼�ɹ���֮������Ϣ����Ҫ���չ涨��ʽ�����ǳƷ�����Ϣ
 * ����3���ͻ��˵�¼�󣬷����Ѿ����úõĻ�ӭ��Ϣ�������������ͻ��ˣ�����֪ͨ�����ͻ��˸ÿͻ�������
 * ����4���������յ��ѵ�¼�ͻ����������ݣ�ת����������¼�ͻ��ˡ�
 * <p>
 * TODO �ͻ������߼��
 */
public class ChatRoomServer {

	static final int port = 9999;
	private static final String USER_EXIST = "system message: user exist, please change a name";
	private static final String USER_CONTENT_SPILIT = "#@#";
	private static final String CLIENT_QUIT = "client_quit";
	public static HashMap<SelectionKey, Long> activeTimer;// ��¼�ϴλ��ʱ��
	private static HashSet<String> users = new HashSet<String>();// ������¼�����������Լ��ǳ�
	private static boolean flag = false;
	private Selector selector = null;
	private Charset charset = Charset.forName("UTF-8");

	// TODO Ҫ���ܼ�����ߣ��Ͳ�����ôͳ����
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
		// ����һ�����߳�����ȡ�ӷ������˵�����
		new Thread(new ActiveCheck()).start();

		selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(port));

		ssc.configureBlocking(false);// �������ķ�ʽ
		ssc.register(selector, SelectionKey.OP_ACCEPT); // ע�ᵽѡ�����ϣ�����Ϊ����״̬

		System.out.println("Server is listening now...");

		while (true) {
			int readyChannels = selector.select();
			if (readyChannels == 0)
				continue;
			Set selectedKeys = selector.selectedKeys(); // ����ͨ�����������֪������ͨ���ļ���
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
			// ������ģʽ
			sc.configureBlocking(false);
			// ע��ѡ������������Ϊ��ȡģʽ���յ�һ����������Ȼ����һ��SocketChannel����ע�ᵽselector�ϣ�֮��������ӵ����ݣ��������SocketChannel����
			sc.register(selector, SelectionKey.OP_READ);

			System.out.println("Server is listening from client :"
					+ sc.getRemoteAddress());
			sc.write(charset.encode("Please input your name."));
		}
		// �������Կͻ��˵����ݶ�ȡ����
		else if (sk.isReadable()) {
			// ���ظ�SelectionKey��Ӧ�� Channel��������������Ҫ��ȡ
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
				// ���˶�Ӧ��channel����Ϊ׼����һ�ν�������
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
				// ע���û�
				if (arrayContent != null && arrayContent.length == 1) {
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
				// ע�����ˣ�������Ϣ
				else if (arrayContent != null && arrayContent.length > 1) {
					String name = arrayContent[0];
					String message = content.substring(name.length()
							+ USER_CONTENT_SPILIT.length());
					message = name + " say " + message;
					if (users.contains(name)) {
						// ���ط������ʹ����ݵĿͻ���
						BroadCast(selector, sc, message);
					}
				}
			}
		}
	}

	public void BroadCast(Selector selector, SocketChannel except,
	                      String content) throws IOException {
		// �㲥���ݵ����е�SocketChannel��
		for (SelectionKey key : selector.keys()) {
			Channel targetchannel = key.channel();
			// ���except��Ϊ�գ����ط������ʹ����ݵĿͻ���
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
				if (res > 60 * 1000) {// ��ʱ���˳�
					SocketChannel tmpSc = (SocketChannel) tmpSk.channel();
					activeTimer.remove(tmpSk);
					try {
						sendQuitMsg(tmpSk);// ��clientҲ�˳�
						// BroadCast(selector, tmpSc, );// �㲥֪ͨ�����û����˵���
						tmpSk.cancel();// �����˳�����
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