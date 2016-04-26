package design_pattern.singleton;

public class Singleton {

	private static Singleton INSTANCE = null;

	private Singleton() {// 私有构造函数，避免new出新实例

	}

	public static Singleton getInstance() {// 双重同步锁
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
