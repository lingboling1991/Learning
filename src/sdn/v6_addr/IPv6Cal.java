package sdn.v6_addr;

public class IPv6Cal {

	static String topicCode = "001";

	public static void main(String args[]) {
		String topicCodeLength = getTopicLength(topicCode);

		String newTopicCode = "11111111" + "0000" + "1110" + "10"
				+ topicCodeLength + topicCode;

		String finalNewTopicCode = getNewTopicCode(newTopicCode);

		System.out.println(finalNewTopicCode);
	}

	public static String getTopicLength(String topicCode) {
		char[] finalTC = new char[7];
		for (int i = 0; i < finalTC.length; i++) {
			finalTC[i] = '0';
		}

		int len = topicCode.length();
		String topicCodeLength = Integer.toBinaryString(len);
		char[] shortTC = topicCodeLength.toCharArray();
		int fc = 6;
		for (int i = shortTC.length - 1; i >= 0; i--) {
			finalTC[fc] = shortTC[i];
			fc--;
		}
		String res = String.valueOf(finalTC);
		return res;
	}

	public static String getNewTopicCode(String newTopicCode) {

		char[] tmp = new char[128];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = '0';
		}

		char[] parm = newTopicCode.toCharArray();
		for (int i = 0; i < parm.length; i++) {
			tmp[i] = parm[i];
		}

		String res = String.valueOf(tmp);
		return res;
	}
}
