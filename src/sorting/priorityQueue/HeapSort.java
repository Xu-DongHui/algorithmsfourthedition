package sorting.priorityQueue;

import sorting.Example;
/*
 * �����򣺵�0λ����Ԫ��
 * ��1���ѵĹ���׶Σ���ԭʼ������֯���Ž�һ������
 * ��2���³����򣺴Ӷ��а��յ���˳��ȡ������Ԫ�ز��õ�������
 * �ܹ�ͬʱ���ŵ�����ʱ��Ϳռ�ķ���������������Ҳ�ܱ�֤ʹ��o(NlgN)�ıȽϺͺ㶨�Ķ���ռ�
 */

public class HeapSort extends Example {

	public static void sort(Comparable[] a) {
		
		int N = a.length - 1;
//		������СΪ1���Ӷѣ����һ�����������ӽ�㶼�Ѿ��Ƕ��ˣ���ô�ڸý���ϵ���sink()���Խ������һ����
//		��������ѣ�Ҷ�ڵ�ĸ�����N/2;
		for(int k = N/2; k >= 1; k--) {
			sink(a, k, N);
		}
		
//		�����е����Ԫ��ɾ����Ȼ��������С�������пճ�����λ��
//		�������򣬽����ڵ����������ĩβ���ٽ��жѵ�����
		while(N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}
	
	private static void sink(Comparable[] a, int k, int N) {
//		������ڵ���ӽڵ�С���򽫸��ڵ����ӽڵ������Ľڵ㽻�����������½���
		while(2 * k <= N) {
//			�ӽڵ�
			int j = 2 * k;
//			ѡȡ�����ӽڵ��и����ֵ
			if(j < N && less(a[j], a[j + 1]))
				j++;
			if(!less(a[k], a[j]))
				break;
//			�������ӽڵ�
			exch(a, k, j);
//			��������
			k = j;
		}
	}

	public static void main(String[] args) {
		Integer[] a = {10, 1, 4, 2, 9, 3};
		sort(a);
		for(int i : a) {
			System.out.print(i);
		}
	}
	
	
}
