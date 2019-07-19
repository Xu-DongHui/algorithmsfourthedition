package sorting.quicksort;

import sorting.Example;
/*
 * 1.快速排序，原地排序，排序时间为NlogN，在一般应用中比其他排序算法都要快得多
 * 2.快速排序也是分治思想，归并排序是递归调用发生在整个数组处理之前（merge），快速排序是递归调用发生在整个数组处理之后
 * 3.快速排序的缺点在于在切分不平衡数组时，排序会很低效。比如，第一次从最小的元素切分，第二次从第二小的元素切分，每次调用切分函数，只能移动一个元素，会导致大数组切分很多次。
 */

public class Quick extends Example {
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
//	快速排序：这种切分没有考虑等于切分值的元素的情况
	private static void sort(Comparable[] a, int lo, int hi) {
//		当单个元素时，或者没有元素时，直接返回，说吧这部分以及排序好了
		if(hi <= lo) 
			return;
//		对数组进行切分,j左边的元素小于a[j],j右边的元素大于a[j]
		int j = partition(a, lo, hi);
//		递归调用，对左右两边的元素再分别进行排序
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
//	切分数组
	private static int partition(Comparable[] a, int lo, int hi) {
//		i,j定位小元素和大元素
		int i = lo, j = hi + 1;
//		随机取第一个元素为切分的元素
		Comparable v = a[lo];
		
		while(true) {
//			遍历数组，用i定位到大于等于v的元素索引
			while(less(a[++i], v))
//				当i遍历到了hi，说明该数组的所有元素都小于v
				if(i == hi)
					break;
//			遍历数组，用j定位到小于等于v的元素索引
			while(less(v, a[--j]))
//				当j遍历到了lo，说明该数组的所有元素都大于v。这句代码是冗余的，因为不可能比自己小
				if(j == lo)
					break;
//			遍历完了整个数组，且j过界，并定位到了小于v的值，i也过界定位到了大于v的值
			if(i >= j)
				break;
//			交换定位到的小于v和大于v的值，保持左边所有值小于v，右边所有值大于v,继续往下遍历
//			当i和j定位到等于v的元素时，会不必要地将一些等值元素交换
			exch(a, i, j);
		}
//		交换a[j]和v的位置，j为该数组的切分点
		exch(a, lo, j);
		return j;
	}
	
}
