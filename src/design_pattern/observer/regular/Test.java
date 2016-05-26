package design_pattern.observer.regular;

public class Test {
	public static void main(String[] args) {

		Watched girl = new RealWatched();

		Watcher a = new RealWatcher();
		Watcher b = new RealWatcher();
		Watcher c = new RealWatcher();

		girl.addWatcher(a);
		girl.addWatcher(b);
		girl.addWatcher(c);

		girl.notifyWatchers("込込込込込");
	}
}
