package java_basic.multi_thread;

import java.util.Date;

public class ThreadA extends Thread {
	private Date runtime;

	public void run() {
		System.out.println("ThreadA begin.");
		this.runtime = new Date();
		System.out.println("ThreadA end.");
	}
}