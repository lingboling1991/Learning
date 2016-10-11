package algorithm.interview.netease.lede;

import java.util.Scanner;

/**
 * Created by lenovo on 2016-9-27.
 */
public class ReverseString {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		String s = cin.nextLine();
		StringBuffer tmp = new StringBuffer();
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				tmp.append(s.charAt(i));
			} else {
				res += tmp.reverse();//只有StringBuffer才带reverse()函数
				tmp.setLength(0);//将StringBuffer置空
				res += s.charAt(i);
			}
		}
		System.out.println(res + tmp.reverse());
	}
}
