package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(1);
		priorityQueue.add(0);
		priorityQueue.add(3);
		System.out.println(priorityQueue.peek());

		HashMap<Integer, Integer> map = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();

		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int n = in.nextInt();
			int s = in.nextInt();
			in.nextLine();

			String[] order = new String[n];
			for (int i = 0; i < n; i++) {
				String str = in.nextLine();
				String[] ss = str.split(" ");

				order[i] = ss[0];

			}
		}
	}
}