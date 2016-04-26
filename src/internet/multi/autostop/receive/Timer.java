package internet.multi.autostop.receive;

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
