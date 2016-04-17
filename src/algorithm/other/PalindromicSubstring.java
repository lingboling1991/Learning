package algorithm.other;

public class PalindromicSubstring {
	public static String getLongest(String src) {
		int start = 0, end = 0;

		for (int i = 0; i < src.length(); i++) {
			int len1 = expanding(src, i, i);
			int len2 = expanding(src, i, i + 1);
			int len = Math.max(len1, len2);

			if (len > end - start) {// 注意这里end - start是会比同样位置算出来的len小1的，因为[0,
									// 2]实际上包括3个字符
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return src.substring(start, end + 1);
	}

	public static int expanding(String s, int left, int right) {
		int l = left, r = right;

		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l -= 1;
			r += 1;
		}

		return r - l - 1;// 这里之所以要-1而不是+1，例如bcaad，此时l在c，r在d
	}

	public static void main(String[] args) {
		String string = getLongest("abacdfgdcaba");
		System.out.println(string);
	}
}
