/**
 * StringUtil.java Nov 25, 2009
 * <p/>
 * Copyright 2009 xwz, Inc. All rights reserved.
 */
package internet.transpass.util;

import java.util.StringTokenizer;

/**
 * �ַ����ָ��
 *
 * @author xwz
 * @version 1.0, Nov 25, 2009 11:45:45 PM
 */
public class StringUtil {
	// ����splitor�ָ�src
	public static String[] splitString(String src, String splitor) {
		StringTokenizer s = new StringTokenizer(src, splitor);

		String[] strs = new String[s.countTokens()];
		int i = 0;
		while (s.hasMoreTokens()) {
			strs[i++] = s.nextToken();
		}

		return strs;
	}
}
