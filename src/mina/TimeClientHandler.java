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
		 * //Դ�� MinaUtil.deTCPTotalCount(); //�ӱ����ͨ����ɾ��
		 * MsgQueueMgr.getDest_session().remove(session.getAttribute("addr"));
		 * //�ر����� session.getService().dispose();
		 */
	}

	// �����ӿ���ʱ�����˷���.
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		// ClientTest.TCPMessageIdle(session, status);

		System.out.println("���ӿ���");

		// session.close(true); //close right now���ر�ͨ��

	}

	// ���ӿ������������׳��쳣δ������ʱ�����˷���
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		System.out.println("���������׳��쳣" + cause.toString());
		// session.close(true); //close right now���ر�ͨ��
	}

}
