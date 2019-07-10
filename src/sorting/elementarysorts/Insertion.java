package sorting.elementarysorts;

import sorting.Example;
/*
 * 1.�������������������˿ˡ��������Ԫ�ز��뵽����Ѿ������Ԫ���е��ʵ�λ�ã�Ϊ�˸������Ԫ���ڳ��ռ䣬��Ҫ�����ڲ���Ԫ�ص�����Ԫ�ض������ƶ�һλ��
 * 2.���������ʱ��ȡ��������Ԫ�صĳ�ʼλ�ã��Բ�����������������Ҫ��ܶࡣ
 * 3.���Ӷȷ�������������o(n^2)�αȽϺ�o(n^2)�ν�������õ������o(n)�αȽϺ�0�ν�����
 */
public class Insertion extends Example {
	public static void sort(Comparable[] a) {
		int N = a.length;
		//forѭ����ִ�й��̣�1.��ʼ��int i = 1��2.�ж�i < N�����Ϊtrue��ִ��{}�������false������ѭ����3.i++����ִ���ж�
		for(int i = 1; i < N; i++) {
			//��a[i]���뵽a[i-1],a[i-2],a[i-3]...֮�У�i֮ǰ��Ԫ���Ѿ�����
			for(int j = i; j >= 1 && less(a[j], a[j-1]); j--) {
				exch(a, j, j - 1);
			}
			//a[i]�Ѿ�����ز��뵽i֮ǰ��Ԫ����ȥ��
		}
	}
	
	public static void main(String[] args) {
		Insertion text = new Insertion();
		Integer[] a = {1, 3, 5, 2, 0, 4};
		text.sort(a);
		text.show(a);
	}
}
