package sdn.route_cal;

import sdn.route_cal.Dijkstra;

import java.util.ArrayList;

public class SDN_PubSub {

	private static int M = 10000;
	private static long start_time;
	private static long end_time;
	private static long interval;

	public static ArrayList<int[]> getEachRoute(int[][] connected,
			ArrayList<Integer> subers) {

		start_time = System.currentTimeMillis();

		int[][] weight = new int[connected.length][connected[0].length];
		for (int i = 0; i < connected.length; i++) {
			for (int j = 0; j < connected[0].length; j++) {
				weight[i][j] = connected[i][j];// 会更改connected的值，所以需要预先存一份
			}
		}

		ArrayList<int[]> res = new ArrayList<int[]>();

		for (int k = 0; k < subers.size(); k++) {
			int start = subers.get(k);

			int[][] path = Dijkstra.getEachStop(connected, start);// path[i][j]到第i个节点路上的第j跳
			connected = weight;
			int[] shortPath = Dijkstra.dijkstra(connected, start);// shortPath[i]到第i个节点的总长度

			int shortestPath = M;
			int shortestPathNum = 0;
			for (int i = 0; i < shortPath.length; i++) {
				if (shortPath[i] > 0 && shortPath[i] < shortestPath) {
					shortestPath = shortPath[i];
					shortestPathNum = i;
				}
			}

			// ！这里需要确保有至少一条联通路
			int[] eachStop = path[shortestPathNum];
			res.add(eachStop);
		}

		end_time = System.currentTimeMillis();
		interval = end_time - start_time;
		System.out.println("It takes " + interval + " to calculate the route");

		return res;
	}

	public static void main(String args[]) {

		int[][] weight = { { 0, 10, M, 30, 100 }, { M, 0, 50, M, M },
				{ M, M, 0, M, 10 }, { M, M, 20, 0, 60 }, { M, M, M, M, 0 } };

		ArrayList<Integer> subers = new ArrayList<Integer>();
		for (int i = 0; i < weight.length; i++) {
			subers.add(i);
		}

		ArrayList<int[]> res = getEachRoute(weight, subers);

		for (int i = 0; i < res.size(); i++) {
			int[] stops = res.get(i);
			System.out.println("Route No." + i);
			for (int j = 0; j < stops.length; j++) {
				if (stops[j] != M) {
					System.out.println(stops[j]);
				} else {
					break;
				}
			}
		}
	}
}
