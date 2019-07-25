package Strings.stringSorts;

/*
 * 1.�����ַ����������򣺸��ݼ������ַ����������з֣������м��������е���һ���ַ������ݹ���ã������ܹ������ַ�����ͬ�Ĳ��֡�
 * 2.�����ַ������������ܹ��ܺõش����ֵ�����нϳ�����ǰ׺�ļ���ȡֵ��Χ��С�ļ���С���飨��Щ���Ǹ�λ���ȵ��ַ��������㷨���ó��ģ�
 * 3.�����ַ�������������Ҫ����Ŀռ䣨���˵ݹ��������ʽջ�⣩
 */

public class Quick3string {
	private static int charAt(String s, int d) {
		if(d < s.length())
			return s.charAt(d);
		else
//			�ַ�������Ϊd
			return -1;
	}
	
	public static void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
	
	private static void sort(String[] a, int lo, int hi, int d) {
//		��lo��hi֮���Ԫ�ؽ�������
		if(lo >= hi)
			return;
//		lo ~ lt - 1���С��v��Ԫ�أ�gt + 1 ~ hi��Ǵ���v��Ԫ�أ�lt ~ gt֮���Ԫ�ص���v
		int lt = lo, gt = hi;
//		�з�Ԫ��
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while(i <= gt) {
			int t = charAt(a[i], d);
//			С�ķ�ltǰ��
			if(t < v)
				exch(a, lt++, i++);
//			��ķ�gt����
			else if(t > v)
				exch(a, i, gt--);
			else
				i++;
		}
		
		sort(a, lo, lt - 1, d);
//		��v����0������ڵ�d���ַ����ַ�������v����-1ʱ����Ȼ��������ȵ�����Ԫ�أ�����Ϊ�ַ������ַ��Ѿ����������ˣ���˲���Ҫ���������ˡ�
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
