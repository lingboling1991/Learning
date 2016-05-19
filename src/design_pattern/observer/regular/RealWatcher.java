package design_pattern.observer.regular;

public class RealWatcher implements Watcher {

	@Override
	public void update(String s) {
		System.out.println("I see this" + s);
	}

}
