package sorting;

import edu.princeton.cs.algs4.StdOut;
/*
 * 1.排序算法的模板，包含了常见排序算法的所有操作和习惯约定
 * 2.排序算法可以分为两类：
 * 	（1）除了函数调用所需的栈和固定数目的实例变量之外，无需额外内存的的原地排序算法
 * 	（2）需要额外内存空间来存储另一份数组副本的其他排序算法
 */
public class Example {
	//排序过程
	public static void sort(Comparable[] a) {
		
	}
	//比较两个元素的大小，v<w为-1
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	//交换位置i,j的元素
	public static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	//单行打印数组
	public static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	//判断数组是否有序
	public static boolean isSorted(Comparable[] a) { 
		for(int i = 1; i < a.length; i++) {
			//后面的元素与前面的元素相比
			if(less(a[i], a[i-1]))
				return false;
		}
		return true;
	}
}
