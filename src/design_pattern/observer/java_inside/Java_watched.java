package design_pattern.observer.java_inside;

import java.util.Observable;

public class Java_watched extends Observable {
	public static void main(String[] args) {
		Java_watched obj_being_watched = new Java_watched();// 被观察者

		Java_watcher watcher_1 = new Java_watcher();// 观察者1
		watcher_1.setName("1");
		obj_being_watched.addObserver(watcher_1);

		Java_watcher watcher_2 = new Java_watcher();// 观察者2
		watcher_2.setName("2");
		obj_being_watched.addObserver(watcher_2);

		obj_being_watched.setChanged();// 如果被观察者改变了，那么必须调用setChanged使之生效

		// 当它准备通知观测程序它的改变时，它必须调用notifyObservers()方法，这导致了在观测对象中对update()方法的调用。
		// 如果在调用notifyObservers()方法之前没有调用setChanged()方法，就不会有什么动作发生。
		obj_being_watched.notifyObservers("Change Name Please.");
	}
}
