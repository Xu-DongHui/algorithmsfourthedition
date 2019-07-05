package sorting.elementarysorts;

import sorting.Example;
/*
 * 1.希尔排序改进了插入排序，交换不相邻的元素以对元素进行局部排序，最终用插入排序将局部有序的数组排序
 * （1）h有序数组，数组中任意相隔为h的元素都是有序的，相当于h个有序数组编织在一起组成的数组
 * 2.希尔排序已经是一种很高效的排序了，可以先用希尔排序，再考虑是否值得将它更换为其他更加复杂的排序
 */
public class Shell extends Example {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
//		 设置最大步长

		while(h < N/3)
			h = 3 * h + 1;
//		在排序过程中，进行逐步排序，先排序间隔最大步长的部分的数组，然后慢慢缩小步长，直到步长为1，进行一个一个的精确排序
//		当步长为1时，就是插入排序
//		从N/3开始，递减至1
		while(h >= 1) {
//			从前往后排序，前面的部分是有序的
			for(int i = h; i < N; i++) {
//				先将步长间隔的数列进行排序，在h-子数组中，将每个元素交换到比它大的元素之前去
				for(int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					exch(a, j, j-h);
			}
//			缩小步长，直到为1
			h = h/3;
		}
			
	}
}
