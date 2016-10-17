package algorithm.interview.indeed;

import java.util.Scanner;

public class Paint {
	static int maxn = 20000 + 10;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		double buf[] = new double[maxn];
		buf[0] = 0;
		buf[1] = 1;
		for (int i = 2; i < maxn; i++) {
			double ans = i;
			for (int j = 0; j < i; j++) {
				if (j >= 1) ans += buf[j - 1];
				if (j <= i - 2) ans += buf[i - 2 - j];
			}
			buf[i] = ans / i;
		}

		String s = String.valueOf(buf[n]);
		s += "00000000000000";
		s = s.substring(0, 14);
		System.out.println(s);
	}
}
