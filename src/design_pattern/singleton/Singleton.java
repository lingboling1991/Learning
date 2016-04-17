package design_pattern.singleton;

public class Singleton {

	private static Singleton INSTANCE = null;

	private Singleton() {// 私有构造函数，避免new出新实例
		
	}

	public static Singleton getInstance() {
		synchronized (Singleton.class) {// 在这里同步，粒度较细
			if (INSTANCE == null) {
				INSTANCE = new Singleton();
			}
		}
		return INSTANCE;
	}
}
