package internet.multi.autostop.receive;

public class ReceiveControl {

	public static void main(String[] args) {
		Flag flag = Flag.getInstance();
		flag.setStop(false);

		Thread thread1 = new Thread(new Receive());
		Thread thread2 = new Thread(new Timer());

		thread1.start();
		thread2.start();
	}

}
