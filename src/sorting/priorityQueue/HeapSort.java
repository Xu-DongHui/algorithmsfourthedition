package sorting.priorityQueue;

import sorting.Example;
/*
 * 堆排序：第0位不放元素
 * （1）堆的构造阶段：将原始数据组织安排进一个堆中
 * （2）下沉排序：从堆中按照递增顺序取出所有元素并得到排序结果
 * 能够同时最优地利用时间和空间的方法，在最坏的情况下也能保证使用o(NlgN)的比较和恒定的额外空间
 */

public class HeapSort extends Example {

	public static void sort(Comparable[] a) {
		
		int N = a.length - 1;
//		跳过大小为1的子堆：如果一个结点的两个子结点都已经是堆了，那么在该结点上调用sink()可以将它变成一个堆
//		构造有序堆；叶节点的个数是N/2;
		for(int k = N/2; k >= 1; k--) {
			sink(a, k, N);
		}
		
//		将堆中的最大元素删除，然后放入堆缩小后数组中空出来的位置
//		数组有序，将根节点调换到数组末尾，再进行堆的有序化
		while(N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}
	
	private static void sink(Comparable[] a, int k, int N) {
//		如果父节点比子节点小，则将父节点与子节点中最大的节点交换，不断向下交换
		while(2 * k <= N) {
//			子节点
			int j = 2 * k;
//			选取两个子节点中更大的值
			if(j < N && less(a[j], a[j + 1]))
				j++;
			if(!less(a[k], a[j]))
				break;
//			交换父子节点
			exch(a, k, j);
//			不断往下
			k = j;
		}
	}

	public static void main(String[] args) {
		Integer[] a = {10, 1, 4, 2, 9, 3};
		sort(a);
		for(int i : a) {
			System.out.print(i);
		}
	}
	
	
}
