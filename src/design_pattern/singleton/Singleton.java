package design_pattern.singleton;

public class Singleton {

	private static Singleton INSTANCE = null;

	private Singleton() {// ˽�й��캯��������new����ʵ��
		
	}

	public static Singleton getInstance() {
		synchronized (Singleton.class) {// ������ͬ�������Ƚ�ϸ
			if (INSTANCE == null) {
				INSTANCE = new Singleton();
			}
		}
		return INSTANCE;
	}
}
