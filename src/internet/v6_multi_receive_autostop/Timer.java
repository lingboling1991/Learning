package internet.v6_multi_receive_autostop;

public class Timer implements Runnable {

	@Override
	public void run() {
		try {
			Flag flag = Flag.getInstance();

			Thread.sleep(4000);

			flag.setStop(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
