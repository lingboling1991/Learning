package java_basic.multi_thread;

import java.util.Date;

public class ThreadB implements Runnable {
	private Date runtime;

	public void run() {
		System.out.println("ThreadB begin.");
		this.runtime = new Date();
		System.out.println("ThreadB end.");
	}
}