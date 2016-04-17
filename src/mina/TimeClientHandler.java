package mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class TimeClientHandler extends IoHandlerAdapter {

	public static int count = 0;

	public void messageReceived(IoSession session, Object message)
			throws Exception {

		// String content = message.toString();
		// count++;
		// System.out.println("client receive a message is : " + content);
	}

	public void messageSent(IoSession session, Object message) throws Exception {

		// if (count % 10 == 0) {
		// System.out.println("Message sent count " + count);
		// }
	}

	@Override
	public void sessionClosed(IoSession session) {
		// ClientTest.TCPSessionClosed(session);

		/*
		 * System.out.println("one Clinet Disconnect !");
		 * 
		 * //源码 MinaUtil.deTCPTotalCount(); //从保存的通道中删除
		 * MsgQueueMgr.getDest_session().remove(session.getAttribute("addr"));
		 * //关闭连接 session.getService().dispose();
		 */
	}

	// 当连接空闲时触发此方法.
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		// ClientTest.TCPMessageIdle(session, status);

		System.out.println("连接空闲");

		// session.close(true); //close right now，关闭通道

	}

	// 当接口中其他方法抛出异常未被捕获时触发此方法
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		System.out.println("其他方法抛出异常" + cause.toString());
		// session.close(true); //close right now，关闭通道
	}

}
