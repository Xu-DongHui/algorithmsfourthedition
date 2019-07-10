package sorting.elementarysorts;

import sorting.Example;

/*
 * 1.ð������
 * ��1���������飬ÿ�α���ʱ���������ǰ�������εıȽ������������Ĵ�С�����ǰ�߱Ⱥ��ߴ��򽻻����ǵ�λ�á�
 * ��2��һ�α���������Ԫ�ؾ������е�ĩβ�������ڸô������У�Ҳ����������Ԫ�ص������Խ����˵�����
 * ��3���Դ����ƣ��൱�ڣ��������������ϸ���ˮ���ϡ�
 */

public class Bubble extends Example{
	public static void sort(Comparable[] a) {
		 int N = a.length;
		 int flag;
//		 ����������һ��Ԫ�ؿ�ʼ����Ԫ���ұ�ȫ������
		 for(int i = N - 1; i > 0; i--) {
			 flag = 0;
//			 �ӵ�һ��Ԫ�ؿ�ʼ��������������Ԫ��λ�ã�������Ԫ�طŵ�iλ
			 for(int j = 0; j < i; j++) {
				 if(less(a[j + 1], a[j])) {
					 flag = 1;
					 exch(a, j, j + 1);
				 }
			 }
			 
//			 ���û�н��н�����˵�������Ѿ�����
			 if(flag == 0)
				 break;
		 }
	}
}
