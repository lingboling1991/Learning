package design_pattern.observer.java_inside;

import java.util.Observable;
import java.util.Observer;

public class Java_watcher implements Observer {

	private String name;

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("�ҵ������ǣ�" + name + "�������ڹ۲죺" + o.getClass().getName()
				+ "���������ĸı����ǣ�" + arg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
