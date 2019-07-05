package sorting.mergesort;

/*
 * 1.自底向上的归并排序：首先进行两两排序（将每个元素想象成一个大小为1的数组，然后是四四归并，然后八八归并，一直下去
 */
public class MergeBU extends Merge{
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
//		sz代表子数组的大小，两个大小为sz是数组归并为一个大小为2 * sz的数组
		for(int sz = 1; sz < N; sz = sz + sz) {
//			lo是两个子数组的第一个元素的索引，不断处理所有的底层的两两归并的子数组
			for(int lo = 0; lo < N - sz; lo += sz + sz)
//				lo, min(lo + sz - 1), hi(lo + sz + sz -1)
//				总会遇到一个lo < N -sz的情况，将sz的数组与剩下的最后一部分进行归并
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz -1, N - 1));
		}
	}
}
