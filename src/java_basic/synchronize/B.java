package java_basic.synchronize;

public class B implements Runnable {
	public static void main(String[] args) {
		B t1 = new B();
		Thread ta = new Thread(t1, "A");
		Thread tb = new Thread(t1, "B");
		ta.start();
		tb.start();
	}

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()
						+ " synchronized loop " + i);
			}
		}
	}
}