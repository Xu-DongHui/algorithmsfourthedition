package sorting.quicksort;
/*
 * 1.�����зֿ������򣬴����ظ�ֵ
 */
public class Quick3way extends Quick {
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
//		lt���ͷ��i����Ԫ�أ�gt���β
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		
//		a[lo,...,lt-1]��С��v��Ԫ�أ�a[gt+1,...,hi]�Ǵ���v��Ԫ�أ�a[lt,i-1]�ǵ���v��Ԫ�أ�a[i,...,gt]��δȷ����Ԫ��
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0)
//				�������ظ���v
				exch(a, lt++, i++);
			else if(cmp > 0)
//				��������Ԫ��
				exch(a, i, gt--);
			else
				i++;
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
		
	}
}
