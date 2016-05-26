package internet.multi.autostop.send;

public class Flag {

	private boolean stop;

	private static Flag f = new Flag();

	private Flag() {
	}

	public static Flag getInstance() {
		return f;
	}

	public synchronized boolean isStop() {
		return stop;
	}

	public synchronized void setStop(boolean stop) {
		this.stop = stop;
	}
}