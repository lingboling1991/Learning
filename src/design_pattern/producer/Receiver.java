package design_pattern.producer;

import java.util.concurrent.BlockingQueue;

public class Receiver implements Runnable {

	private final BlockingQueue<String> queue;

	public Receiver(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {//ÿ������һ�ζ��У������ݾͶ�����
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
