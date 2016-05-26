package design_pattern.producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Center {
	public static void main(String args[]) {
		final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

		for (int i = 0; i < 1; i++) {
			new Thread(new Receiver(queue)).start();
		}

		for (int i = 0; i < 1; i++) {
			new Thread(new Sender(queue)).start();
		}
	}
}
