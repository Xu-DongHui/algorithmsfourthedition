package sorting.elementarysorts;

import sorting.Example;

/*
 * 1.冒泡排序：
 * （1）遍历数组，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；如果前者比后者大，则交换它们的位置。
 * （2）一次遍历后，最大的元素就在数列的末尾，并且在该次排序中，也对其他数组元素的有序性进行了调整。
 * （3）以此类推，相当于，将最大的数慢慢上浮到水面上。
 */

public class Bubble extends Example{
	public static void sort(Comparable[] a) {
		 int N = a.length;
		 int flag;
//		 标记数组最后一个元素开始，该元素右边全部有序
		 for(int i = N - 1; i > 0; i--) {
			 flag = 0;
//			 从第一个元素开始遍历，调整数组元素位置，将最大的元素放到i位
			 for(int j = 0; j < i; j++) {
				 if(less(a[j + 1], a[j])) {
					 flag = 1;
					 exch(a, j, j + 1);
				 }
			 }
			 
//			 如果没有进行交换，说明数组已经有序
			 if(flag == 0)
				 break;
		 }
	}
}
