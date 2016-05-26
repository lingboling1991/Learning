package java_basic.multi_thread;

public class Test {
	public static void main(String[] args) {

		Test test = new Test();
		test.starta();
		test.startb();
	}

	public void starta() {
		Thread threada = new ThreadA();
		threada.start();
	}

	public void startb() {
		Runnable threadb = new ThreadB();
		Thread thread = new Thread(threadb);
		thread.start();
	}
}
