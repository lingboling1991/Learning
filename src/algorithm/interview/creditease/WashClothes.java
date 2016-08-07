package algorithm.interview.creditease;

import java.util.Scanner;

class Cloth {// ��ʾһ��Ҫϴ���·�
	int time;// ϴ����·���ʱ��
	String color;// ����·�����ɫ

	public Cloth() {
		this.time = 0;
		this.color = null;
	}

	public Cloth(int time, String color) {
		this.time = time;
		this.color = color;
	}
}

public class WashClothes {

	private int n;// ��ɫ������
	private int m;// �·�����
	private String colors[];// ��ɫ���飬�������е���ɫ
	private Cloth cloths[];// �·����飬��������Ҫϴ���·�

	private int res;// ϴ��ȫ���·����������ʱ��

	public WashClothes(int n, int m, String colors[], Cloth cloths[]) {
		this.n = n;
		this.m = m;
		this.colors = colors;
		this.cloths = cloths;
		res = 0;
	}

	public static void main(String args[]) {
		String colors[];
		Cloth cloths[];
		Scanner in = new Scanner(System.in);
		while (true) {

			int n = in.nextInt();// n����ɫ
			int m = in.nextInt();// m���·�
			if (n == 0 && m == 0)
				break;
			colors = new String[n];
			cloths = new Cloth[m];
			for (int i = 0; i < n; i++)
				colors[i] = in.next();

			for (int i = 0; i < m; i++) {
				cloths[i] = new Cloth();
				cloths[i].time = in.nextInt();
				cloths[i].color = in.next();
			}
			WashClothes wc = new WashClothes(n, m, colors, cloths);
			wc.dp();
		}
	}

	public void dp() {

		int r[] = new int[n];// r[j]��ʾһ����ϴcolors[j]������ɫ���·����ܵ�ϴ��ʱ��
		int dp[] = new int[50000];
		/*
		 * dp[j]��ʾ��ʱ��j���������ڣ�һ����ϴĳ����ɫ���·���һ������ϴ�����ϴ��ʱ�䣨��ֵ���� �ܵ��·���< 100��ÿ���·���ϴ��ʱ��<
		 * 1000��100*1000/2=50000����ͳ01����֪����Ʒ���������ﲻ֪�����������õ���Ŀ����������
		 *
		 * ����ϴÿ���·���ʱ��������Ҳ�Ǽ�ֵ����˶���һά����ȿ�
		 */

		for (int i = 0; i < m; i++) {// ��ÿһ���·�
			for (int j = 0; j < n; j++) {// ��������һ�ֵ���ɫ
				if (cloths[i].color.equals(colors[j])) {
					r[j] += cloths[i].time;// ����������ɫ���·��ܵ�ϴ��ʱ��
					break;
				}// ����Ҳ������HashMap�����������Ҫn^2���Ӷ�������
			}
		}

		for (int i = 0; i < n; i++) {// ��ÿһ����ɫ��n��01�������⣩
			for (int j = 0; j <= r[i] / 2; j++)
				dp[j] = 0;
			for (int j = 0; j < m; j++) {// �������·�����������ɫ���·�
				if (cloths[j].color.equals(colors[i])) {// ���ÿ��������ɫ���·�
					for (int k = r[i] / 2; k >= cloths[j].time; k--) {
						if (dp[k] < dp[k - cloths[j].time] + cloths[j].time)
							dp[k] = dp[k - cloths[j].time] + cloths[j].time;
						// ��ֽ��дһ��Ϳ������ˣ�����
					}
				}
			}
			res += r[i] - dp[r[i] / 2];// ϴ��ȫ���·����������ʱ�� = ϴ������ɫ�·���������ʱ��ĺ�
		}
		System.out.println(res);
	}
}