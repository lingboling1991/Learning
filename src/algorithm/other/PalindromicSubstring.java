package algorithm.other;

public class PalindromicSubstring {
	// ������Ӵ�
	public static String getLongest(String src) {
		int start = 0, end = 0;

		for (int i = 0; i < src.length(); i++) {
			int len1 = expanding(src, i, i);
			int len2 = expanding(src, i, i + 1);
			int len = Math.max(len1, len2);

			if (len > end - start) {// ע������end - start�ǻ��ͬ��λ���������lenС1�ģ���Ϊ[0,
				// 2]ʵ���ϰ���3���ַ�
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

		return r - l - 1;// ����֮����Ҫ-1������+1������bcaad����ʱl��c��r��d
	}

	public static void main(String[] args) {
		String string = getLongest("abacdfgdcaba");
		System.out.println(string);
	}
}
