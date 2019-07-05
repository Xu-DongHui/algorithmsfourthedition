package sorting.elementarysorts;

import sorting.Example;
/*
 * 1.ѡ�����򣺲��ϵ�ѡ��ʣ��Ԫ���е���С�ߣ��ŵ�ǰ��
 * 	��1���ҵ������е���С���Ǹ�Ԫ�أ�����������ĵ�һ��Ԫ�ؽ���
 * 	��2����ʣ�µ�Ԫ�����ҵ���С��Ԫ�أ�����������ĵڶ���Ԫ�ؽ���
 * 	��3���Դ����ƣ�ֱ�����һ��Ԫ��
 * 2.���Ӷȷ�����ѡ��������Ҫo(n^2)�αȽϺ�o(n)�ν���
 * 3.ѡ��������ص㣺
 * 	��1������ʱ��������޹أ�������������������ģ�Ҳ����Ҫo(n^2)�αȽϡ�Ϊ���ҵ���СԪ�ض��������ɨ��һ�飬������Ϊ�´�ɨ���ṩʲô��Ϣ��
 * 	��2�������ƶ����٣�ÿ�ν�������ֻ�ı���������Ԫ�ص�ֵ�����ֻ��o(n)�ν��������������������С�����Թ�ϵ��
 */
public class Selection extends Example {
	//������a��������
	public static void sort(Comparable[] a) {
		int N = a.length;//���鳤��
		//������õ�λ��i��i֮ǰ��Ԫ�ض��Ѿ�����
		for(int i = 0; i < N; i++) {
			int min = i;
			//ѡȡi֮���Ԫ���е���Сֵ
			for(int j = i + 1; j < N; j++) {
				if(less(a[j], a[min])) {
					//��¼��Сֵ
					min = j;
				}
			}
			//��i֮���Ԫ���е���Сֵ��λ��i��Ԫ�ؽ���
			exch(a, i, min);
		}
	}
	
	public static void main(String[] args) {
		Selection text = new Selection();
		Integer[] a = {1, 3, 5, 2, 0, 4};
		text.sort(a);
		text.show(a);
	}
}
