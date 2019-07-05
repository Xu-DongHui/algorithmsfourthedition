package sorting.mergesort;

import sorting.Example;

/*
 * 1.�鲢����
 * ��1���鲢����������������鲢��һ��������������顣
 * ��2���鲢���򣺽�һ�������Ϊ���룬�ֱ������ٰѽ���鲢������
 * ��3���鲢�����ʱ��ΪNlogN,����Ҫ�����N�����ȵĶ���ռ�
 * 2.����˼�룬��һ��������ָ��С����ֱ�����Ȼ�������е�С����Ĵ����������������
 */

public class Merge extends Example {
//	���������ռ�
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}
	
//	�鲢����
	private static void sort(Comparable[] a, int lo, int hi) {
//		��hi  <= loʱ�����������������ֵֻ��һ����������Ԫ����������������飬��˿���ֱ�ӷ���
		if(hi <= lo)
			return;
//		�������Ϊ�����֣����еݹ����
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}
	
//	�Զ����µĹ鲢����
//	������������������й鲢����������ռ�ʵ�ֵ�ԭ�ع鲢
	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
//		���Ƹ�����������aux������
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
//		�����������Ԫ������һһ���бȽϣ�
		for(int k = lo; k <= hi; k++) {
//			������һ���������Ѿ�ȫ���������飬�Ͱ���һ���������ʣ��Ԫ�ذ�˳��ȫ���ŵ�ԭ������
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
//			ȡ��С��Ԫ�ط���ԭ����
			else if(less(aux[i], aux[j]))
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
		}
	}
}
