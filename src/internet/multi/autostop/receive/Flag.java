package internet.multi.autostop.receive;

public class Flag {

	private static Flag f = new Flag();
	private boolean stop;

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