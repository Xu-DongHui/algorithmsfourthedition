package Strings.stringSorts;

/*
 * 1.三向字符串快速排序：根据键的首字符进行三向切分，仅在中间子数组中的下一个字符继续递归调用，这样能够跳过字符串相同的部分。
 * 2.三向字符串快速排序能够很好地处理等值键，有较长公共前缀的键，取值范围较小的键和小数组（这些都是高位优先的字符串排序算法不擅长的）
 * 3.三向字符串快速排序不需要额外的空间（除了递归所需的隐式栈外）
 */

public class Quick3string {
	private static int charAt(String s, int d) {
		if(d < s.length())
			return s.charAt(d);
		else
//			字符串长度为d
			return -1;
	}
	
	public static void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
	
	private static void sort(String[] a, int lo, int hi, int d) {
//		对lo到hi之间的元素进行排序
		if(lo >= hi)
			return;
//		lo ~ lt - 1标记小于v的元素；gt + 1 ~ hi标记大于v的元素；lt ~ gt之间的元素等于v
		int lt = lo, gt = hi;
//		切分元素
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while(i <= gt) {
			int t = charAt(a[i], d);
//			小的放lt前面
			if(t < v)
				exch(a, lt++, i++);
//			大的放gt后面
			else if(t > v)
				exch(a, i, gt--);
			else
				i++;
		}
		
		sort(a, lo, lt - 1, d);
//		当v大于0代表存在第d个字符的字符串；当v等于-1时，虽然可能有相等的数组元素，但因为字符串的字符已经都遍历过了，因此不需要进行排序了。
		if(v >= 0)
			sort(a, lt, gt, d - 1);
		sort(a, gt + 1, hi, d);
		
	}
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable v = a[i];
		a[i] = a[j];
		a[j] = v;
	}
}
