package sorting.quicksort;
/*
 * 1.三向切分快速排序，处理重复值
 */
public class Quick3way extends Quick {
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
//		lt标记头，i遍历元素，gt标记尾
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		
//		a[lo,...,lt-1]是小于v的元素，a[gt+1,...,hi]是大于v的元素，a[lt,i-1]是等于v的元素，a[i,...,gt]是未确定的元素
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0)
//				换的是重复的v
				exch(a, lt++, i++);
			else if(cmp > 0)
//				换的是新元素
				exch(a, i, gt--);
			else
				i++;
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
		
	}
}
