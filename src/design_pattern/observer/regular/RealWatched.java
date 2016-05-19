package design_pattern.observer.regular;

import java.util.ArrayList;

public class RealWatched implements Watched {

	private ArrayList<Watcher> watchers = new ArrayList<Watcher>();

	@Override
	public void addWatcher(Watcher w) {
		watchers.add(w);
	}

	@Override
	public void delWatcher(Watcher w) {
		watchers.remove(w);
	}

	@Override
	public void notifyWatchers(String s) {
		for (Watcher w : watchers) {
			w.update(s);
		}
	}

}
