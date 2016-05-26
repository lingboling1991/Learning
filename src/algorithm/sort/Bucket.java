package algorithm.sort;

import java.util.ArrayList;
import java.util.List;

//http://blog.csdn.net/fg2006/article/details/6677465
//http://www.cnblogs.com/kkun/archive/2011/11/23/bucket_sort.html

public class Bucket {
	// ��֪�����䣬����[1..10]��ѧ���ķ���[0...100]�ȣ�����Ƚ����ݷ�ɢ�������ú���ȡӳ����Ϊsorted������±�
	// ������ظ�������,����Ҫ List<int>���飬����ٵ�����û���ظ�������
	public static List<Integer> bucket(int[] unsorted) {
		int max = unsorted[0];
		for (int i = 1; i < unsorted.length; i++) {
			max = Math.max(max, unsorted[i]);
		}

		Integer[] sorted = new Integer[max + 1];// ������Ͱ�����࣬�����ö�ά�����ʾһ�������̶���СͰ����СͰ�ﻹ��Ҫ����
		for (int i = 0; i < unsorted.length; i++) {
			sorted[unsorted[i]] = unsorted[i];// f(x)ӳ�䣬List<>���ظ�����������
		}

		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < sorted.length; i++) {
			if (sorted[i] != null) {
				res.add(sorted[i]);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] src = {9, 8, 4, 3, 2, 1, 7, 6, 5};
		System.out.println(bucket(src).toString());
	}
}
