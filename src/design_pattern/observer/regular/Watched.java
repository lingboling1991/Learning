package design_pattern.observer.regular;

public interface Watched {
	public void addWatcher(Watcher w);

	public void delWatcher(Watcher w);

	public void notifyWatchers(String s);
}
