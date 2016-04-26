package internet.multi.autostop.send;

public class Timer implements Runnable {

	@Override
	public void run() {
		try {
			Flag flag = Flag.getInstance();
//			System.out.println("start sleeping");
			Thread.sleep(4000);
//			System.out.println("end sleeping");
			flag.setStop(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
