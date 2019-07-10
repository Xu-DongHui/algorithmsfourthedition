package sorting.elementarysorts;

import sorting.Example;
/*
 * 1.插入排序：类似于整理扑克。将后面的元素插入到左边已经有序的元素中的适当位置，为了给插入的元素腾出空间，需要将大于插入元素的其余元素都向右移动一位。
 * 2.插入排序的时间取决于数组元素的初始位置，对部分有序的数组的排序要快很多。
 * 3.复杂度分析：最坏情况下是o(n^2)次比较和o(n^2)次交换，最好的情况是o(n)次比较和0次交换。
 */
public class Insertion extends Example {
	public static void sort(Comparable[] a) {
		int N = a.length;
		//for循环的执行过程：1.初始化int i = 1；2.判断i < N，如果为true则执行{}，如果是false则跳出循环；3.i++，再执行判断
		for(int i = 1; i < N; i++) {
			//将a[i]插入到a[i-1],a[i-2],a[i-3]...之中，i之前的元素已经有序
			for(int j = i; j >= 1 && less(a[j], a[j-1]); j--) {
				exch(a, j, j - 1);
			}
			//a[i]已经有序地插入到i之前的元素中去了
		}
	}
	
	public static void main(String[] args) {
		Insertion text = new Insertion();
		Integer[] a = {1, 3, 5, 2, 0, 4};
		text.sort(a);
		text.show(a);
	}
}
