package java_basic.synchronize;

public class A {
	public static void main(String[] args) {
		final A test = new A();
		Thread t1 = new Thread(test::m4t1, "t1");
		Thread t2 = new Thread(test::m4t2, "t2");
		t1.start();
		t2.start();
	}

	public void m4t1() {
		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out
						.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
			}
		}
	}

	public void m4t2() {
		int i = 5;
		while (i-- > 0) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ignored) {
			}
		}
	}
}