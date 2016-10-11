package algorithm.graph;

import java.util.*;

/**
 * Created by lenovo on 2016-10-10.
 */
public class Kruskal {
	//http://blog.csdn.net/coslay/article/details/47756917
	//C_ Gr-idian MST - CODE FESTIVAL 2016 qual B _ AtCoder
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int w = in.nextInt();
		int h = in.nextInt();
		int[] p = new int[w];
		int[] q = new int[h];

		for (int i = 0; i < w; i++) {
			p[i] = in.nextInt();
		}
		for (int i = 0; i < h; i++) {
			q[i] = in.nextInt();
		}

		Set<Edge> edges = new TreeSet<>();
		Set<Node> nodes = new HashSet<>();

		for (int i = 0; i <= w; i++) {
			for (int j = 0; j <= h; j++) {
				Node n = new Node(i, j);
				nodes.add(n);

				if (i + 1 <= w) {
					Edge e1 = new Edge(i + ":" + j, (i + 1) + ":" + j, p[i]);
					edges.add(e1);
				}

				if (j + 1 <= h) {
					Edge e2 = new Edge(i + ":" + j, i + ":" + (j + 1), q[j]);
					edges.add(e2);
				}
			}
		}

		Set<Edge> g = KRUSKAL(nodes, edges);

		int count = 0;
		for (Edge ed : g) {
			System.out.println(ed.start + "  ->  " + ed.end + " distance: " + ed.dist);
			count += ed.dist;
		}
		System.out.println(count);
	}

	public static Set<Edge> KRUSKAL(Set<Node> nodes, Set<Edge> edges) {
		Set<Edge> g = new TreeSet<>();
		ArrayList<HashSet<String>> sets = new ArrayList<>();
		for (Node node : nodes) {
			HashSet<String> set = new HashSet<>();
			set.add(node.location);
			sets.add(set);
		}

		for (Edge edge : edges) {
			String start = edge.start;
			String end = edge.end;
			int dist = edge.dist;
			int loca_1 = -1, loca_2 = -2;
			for (int j = 0; j < sets.size(); j++) {
				HashSet<String> set = sets.get(j);
				if (set.contains(start)) {
					loca_1 = j;
				}
				if (set.contains(end)) {
					loca_2 = j;
				}
			}
			if (loca_1 == loca_2) {
			} else {
				System.out.println("choose edge: " + start + "  ->  " + end + "  length: " + dist);
				HashSet<String> set_1 = sets.get(loca_1);
				HashSet<String> set_2 = sets.get(loca_2);

				if (loca_1 > loca_2) {
					sets.remove(loca_1);
					sets.remove(loca_2);
				} else {
					sets.remove(loca_2);
					sets.remove(loca_1);
				}

				set_2.addAll(set_1);
				sets.add(set_2);
				g.add(edge);
			}
		}
		return g;

	}

	static class Node {
		int x;
		int y;
		String location;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.location = x + ":" + y;
		}
	}

	static class Edge implements Comparable {
		String start;
		String end;
		int dist;

		public Edge(String start, String end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Object o) {
			Edge other = (Edge) o;
			if (this.dist > other.dist) {
				return 1;
			} else if (this.dist == other.dist) {
				if ((this.start.equals(other.start) && this.end.equals(other.end))) {
					return 0;
				} else if (this.start.compareTo(other.start) == 0) {
					return this.end.compareTo(other.end);
				} else {
					return this.start.compareTo(other.start);
				}
			} else {
				return -1;
			}
		}
	}
}
