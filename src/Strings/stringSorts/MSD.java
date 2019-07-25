package Strings.stringSorts;

/*
 * 1.高位优先的字符串排序：先用键索引计数法将所有字符的按照首字符进行排序，然后递归地在将每个首字符对应的子数组排序。
 * 2.高位优先的字符串排序会产生大量的微型数组，每次排序都需要将count[]数组初始化。因此必须要将小数组切换到插入排序，提高性能。
 * 3.高位优先的字符串排序通常只需要检查每个键开头的几个字符就可以了
 * 4.高位优先的字符串受限于一些键可能有较长的公共部分。
 */

public class MSD {
	private static int R = 256;
//	小数组的切换阈值
	private static final int M = 15;
	
//	数据分类的辅助数组
	private static String[] aux;
//	charAt()方法将字符串中的字符转换为数组索引，当指定位置超过字符串的末尾时该方法返回-1;
	private static int charAt(String s, int d) {
		if(d < s.length())
			return s.charAt(d);
		else
			return -1;
	}
	
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}
	
//	对数组a，从lo到hi的元素，根据第d个字符，进行键索引计数法
	private static void sort(String[] a, int lo, int hi, int d) {
//		判断插入排序的切换阈值
		if(hi <= lo + M) {
			for(int i = lo + 1; i <= hi; i++) {
				for(int j = i; j > lo && less(a[j], a[j - 1]); j--)
					exch(a, j, j -1);
			}
			return;
		}
		
//		键为0,1,...R-1，因为返回的键要+2，所以count数组的大小为R+2
		int[] count = new int[R + 2];
		
//		计算频率
		for(int i = lo; i <= hi; i++) {
//			计算完后count[]元素的意义
//			count[1]:存储长度为d的字符串的数量，charAt()返回-1
//			count[2]~count[R+1]:存储键0,1,...,R-1的数量
			count[charAt(a[i], d) + 2]++;
		}
		
//		将频率转换为索引
//		将长度为d的字符串放到最前面，是当前数组的最小值，因为前d个字符大家都相等
		for(int r = 0; r < R + 1; r++) {
//			转换完后count[]元素的意义
//			count[0]:为0,存储长度为d的字符串的起始索引
//			count[1]~count[R]:为键0的字符串的起始索引
//			count[R + 1]无用
			count[r + 1] += count[r];
		}
		
//		数据分类
		for(int i = lo; i <= hi; i++) {
			aux[count[charAt(a[i], d) + 1]++] = a[i];
//			数据分类完后count元素的意义
//			count[0]:由0不断地往后迭代，成为了键0的初始索引
//			count[1]~count[R-1]成为了键1,2,...,R-1的初始索引
//			同时，count[1]~count[R]成为了键0,1,2,...,R-1的结束索引+1
		}
		
//		回写数据
		for(int i = lo; i <= hi; i++) {
//			aux[]数组的数据是从0开始存储lo到hi的a[]数组元素
//			a[i]数组的元素是从lo到hi
			a[i] = aux[i - lo];
		}
		
//		递归调用排序，用d+1个字符进行键索引计数法
		for(int r = 0; r < R; r++) {
//			长度为d的字符串不需要进行排序
//			将键0,1,2,...,R-1部分的字符串数组进行排序
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}
	
	private static boolean less(String v, String w) {
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable v = a[i];
		a[i] = a[j];
		a[j] = v;
	}
}
