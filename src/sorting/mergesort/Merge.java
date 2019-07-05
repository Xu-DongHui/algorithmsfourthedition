package sorting.mergesort;

import sorting.Example;

/*
 * 1.归并排序
 * （1）归并：将两个有序数组归并成一个更大的有序数组。
 * （2）归并排序：将一个数组分为两半，分别排序，再把结果归并起来。
 * （3）归并排序的时间为NlogN,但需要引入和N成正比的额外空间
 * 2.分治思想，将一个大问题分割成小问题分别解决，然后用所有的小问题的答案来解决整个大问题
 */

public class Merge extends Example {
//	额外的排序空间
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}
	
//	归并排序
	private static void sort(Comparable[] a, int lo, int hi) {
//		当hi  <= lo时，代表传入排序的数组值只有一个数，而单元素是特殊的有序数组，因此可以直接返回
		if(hi <= lo)
			return;
//		将数组分为两部分，进行递归调用
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}
	
//	自顶向下的归并排序
//	将两个有序子数组进行归并，借助额外空间实现的原地归并
	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
//		复制该排序数组在aux数组上
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
//		将两个数组的元素依次一一进行比较，
		for(int k = lo; k <= hi; k++) {
//			当其中一个子数组已经全部放入数组，就把另一个子数组的剩余元素按顺序全部放到原数组内
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
//			取较小的元素放入原数组
			else if(less(aux[i], aux[j]))
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
		}
	}
}
