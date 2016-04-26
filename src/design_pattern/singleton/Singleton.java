package design_pattern.singleton;

public class Singleton {

	private static Singleton INSTANCE = null;

	private Singleton() {// ˽�й��캯��������new����ʵ��

	}

	public static Singleton getInstance() {// ˫��ͬ����
		if (INSTANCE == null) {
			synchronized (Singleton.class) {
				if (INSTANCE == null) {
					INSTANCE = new Singleton();
				}
			}
		}
		return INSTANCE;
	}
}
