package algorithm.interview.netease;

import java.util.ArrayList;
import java.util.List;

public class G_9 {
	public static void main(String[] args) {
		String s = "49693";
		List<List<String>> res = check(s);
		System.out.println("a");
	}

	public static List<List<String>> check(String s) {
		char[] ch = s.toCharArray();
		List<List<String>> res = new ArrayList<List<String>>();
		int n = ch.length;
		ArrayList<Character> t = new ArrayList<>();

		dfs(ch, t, n, res, 0);

		return res;
	}

	public static void dfs(char[] ch, ArrayList<Character> t, int n,
	                       List<List<String>> res, int level) {
		if (t.size() == n) {
			char[] x = new char[n];
			for (int i = 0; i < t.size(); i++) {
				x[i] = t.get(i);
			}

			ArrayList<String> tmp = new ArrayList<>();
			tmp.add(String.valueOf(x));

			res.add(tmp);
			return;
		}

		for (int i = level; i < n; i++) {
			if (ch[i] == '9') {
				t.add('g');
				dfs(ch, t, n, res, i + 1);
				t.remove(t.size() - 1);
			} else if (ch[i] == 'g') {
				t.add('9');
				dfs(ch, t, n, res, i + 1);
				t.remove(t.size() - 1);
			}

			t.add(ch[i]);
			dfs(ch, t, n, res, i + 1);
			t.remove(t.size() - 1);
		}
	}
}
