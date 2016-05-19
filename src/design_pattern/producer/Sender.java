package design_pattern.producer;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Sender implements Runnable {

	private final BlockingQueue<String> queue;

	public Sender(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				queue.put(new Date().toString());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
