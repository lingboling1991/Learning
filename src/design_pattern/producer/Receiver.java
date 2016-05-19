package design_pattern.producer;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Receiver implements Runnable {

	private final BlockingQueue<String> queue;

	public Receiver(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {//每五秒检查一次队列，有内容就读出来
			try {
				String string = queue.take();
				System.out.println(string);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
