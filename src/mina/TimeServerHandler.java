package mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * ��������ҵ���߼�
 */
public class TimeServerHandler extends IoHandlerAdapter {

	/**
	 * ���Ӵ����¼�
	 */
	@Override
	public void sessionCreated(IoSession session) {
		// ��ʾ�ͻ��˵�ip�Ͷ˿�
		System.out.println(session.getRemoteAddress().toString());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	/**
	 * ��Ϣ�����¼�
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String strMsg = message.toString();
		if (strMsg.trim().equalsIgnoreCase("quit")) {
			session.close(true);
			return;
		}
		// ������Ϣ�ַ���
		session.write("Hi Client!");
		// ��ӡ�ͻ��˴�������Ϣ����
		System.out.println("Message written : " + strMsg);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("IDLE" + session.getIdleCount(status));
	}
}
