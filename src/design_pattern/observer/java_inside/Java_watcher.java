package design_pattern.observer.java_inside;

import java.util.Observable;
import java.util.Observer;

public class Java_watcher implements Observer {

	private String name;

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("我的名字是：" + name + "，我正在观察：" + o.getClass().getName()
				+ "，他发来的改变量是：" + arg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
