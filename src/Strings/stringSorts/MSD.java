package Strings.stringSorts;

/*
 * 1.��λ���ȵ��ַ����������ü������������������ַ��İ������ַ���������Ȼ��ݹ���ڽ�ÿ�����ַ���Ӧ������������
 * 2.��λ���ȵ��ַ�����������������΢�����飬ÿ��������Ҫ��count[]�����ʼ������˱���Ҫ��С�����л�����������������ܡ�
 * 3.��λ���ȵ��ַ�������ͨ��ֻ��Ҫ���ÿ������ͷ�ļ����ַ��Ϳ�����
 * 4.��λ���ȵ��ַ���������һЩ�������нϳ��Ĺ������֡�
 */

public class MSD {
	private static int R = 256;
//	С������л���ֵ
	private static final int M = 15;
	
//	���ݷ���ĸ�������
	private static String[] aux;
//	charAt()�������ַ����е��ַ�ת��Ϊ������������ָ��λ�ó����ַ�����ĩβʱ�÷�������-1;
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
	
//	������a����lo��hi��Ԫ�أ����ݵ�d���ַ������м�����������
	private static void sort(String[] a, int lo, int hi, int d) {
//		�жϲ���������л���ֵ
		if(hi <= lo + M) {
			for(int i = lo + 1; i <= hi; i++) {
				for(int j = i; j > lo && less(a[j], a[j - 1]); j--)
					exch(a, j, j -1);
			}
			return;
		}
		
//		��Ϊ0,1,...R-1����Ϊ���صļ�Ҫ+2������count����Ĵ�СΪR+2
		int[] count = new int[R + 2];
		
//		����Ƶ��
		for(int i = lo; i <= hi; i++) {
//			�������count[]Ԫ�ص�����
//			count[1]:�洢����Ϊd���ַ�����������charAt()����-1
//			count[2]~count[R+1]:�洢��0,1,...,R-1������
			count[charAt(a[i], d) + 2]++;
		}
		
//		��Ƶ��ת��Ϊ����
//		������Ϊd���ַ����ŵ���ǰ�棬�ǵ�ǰ�������Сֵ����Ϊǰd���ַ���Ҷ����
		for(int r = 0; r < R + 1; r++) {
//			ת�����count[]Ԫ�ص�����
//			count[0]:Ϊ0,�洢����Ϊd���ַ�������ʼ����
//			count[1]~count[R]:Ϊ��0���ַ�������ʼ����
//			count[R + 1]����
			count[r + 1] += count[r];
		}
		
//		���ݷ���
		for(int i = lo; i <= hi; i++) {
			aux[count[charAt(a[i], d) + 1]++] = a[i];
//			���ݷ������countԪ�ص�����
//			count[0]:��0���ϵ������������Ϊ�˼�0�ĳ�ʼ����
//			count[1]~count[R-1]��Ϊ�˼�1,2,...,R-1�ĳ�ʼ����
//			ͬʱ��count[1]~count[R]��Ϊ�˼�0,1,2,...,R-1�Ľ�������+1
		}
		
//		��д����
		for(int i = lo; i <= hi; i++) {
//			aux[]����������Ǵ�0��ʼ�洢lo��hi��a[]����Ԫ��
//			a[i]�����Ԫ���Ǵ�lo��hi
			a[i] = aux[i - lo];
		}
		
//		�ݹ����������d+1���ַ����м�����������
		for(int r = 0; r < R; r++) {
//			����Ϊd���ַ�������Ҫ��������
//			����0,1,2,...,R-1���ֵ��ַ��������������
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
