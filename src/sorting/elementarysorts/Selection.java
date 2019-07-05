package sorting.elementarysorts;

import sorting.Example;
/*
 * 1.选择排序：不断地选择剩余元素中的最小者，放到前面
 * 	（1）找到数组中的最小的那个元素，将他和数组的第一个元素交换
 * 	（2）再剩下的元素中找到最小的元素，将他和数组的第二个元素交换
 * 	（3）以此类推，直到最后一个元素
 * 2.复杂度分析：选择排序需要o(n^2)次比较和o(n)次交换
 * 3.选择排序的特点：
 * 	（1）运行时间和输入无关，哪怕输入数组是有序的，也是需要o(n^2)次比较。为了找到最小元素对数组进行扫描一遍，并不能为下次扫描提供什么信息。
 * 	（2）数据移动最少，每次交换都会只改变两个数组元素的值，因此只有o(n)次交换，交换次数和数组大小成线性关系。
 */
public class Selection extends Example {
	//将数组a升序排列
	public static void sort(Comparable[] a) {
		int N = a.length;//数组长度
		//已排序好的位置i：i之前的元素都已经有序
		for(int i = 0; i < N; i++) {
			int min = i;
			//选取i之后的元素中的最小值
			for(int j = i + 1; j < N; j++) {
				if(less(a[j], a[min])) {
					//记录最小值
					min = j;
				}
			}
			//将i之后的元素中的最小值和位置i的元素交换
			exch(a, i, min);
		}
	}
	
	public static void main(String[] args) {
		Selection text = new Selection();
		Integer[] a = {1, 3, 5, 2, 0, 4};
		text.sort(a);
		text.show(a);
	}
}
