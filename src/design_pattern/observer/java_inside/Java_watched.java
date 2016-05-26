package design_pattern.observer.java_inside;

import java.util.Observable;

public class Java_watched extends Observable {
	public static void main(String[] args) {
		Java_watched obj_being_watched = new Java_watched();// ���۲���

		Java_watcher watcher_1 = new Java_watcher();// �۲���1
		watcher_1.setName("1");
		obj_being_watched.addObserver(watcher_1);

		Java_watcher watcher_2 = new Java_watcher();// �۲���2
		watcher_2.setName("2");
		obj_being_watched.addObserver(watcher_2);

		obj_being_watched.setChanged();// ������۲��߸ı��ˣ���ô�������setChangedʹ֮��Ч

		// ����׼��֪ͨ�۲�������ĸı�ʱ�����������notifyObservers()�������⵼�����ڹ۲�����ж�update()�����ĵ��á�
		// ����ڵ���notifyObservers()����֮ǰû�е���setChanged()�������Ͳ�����ʲô����������
		obj_being_watched.notifyObservers("Change Name Please.");
	}
}
