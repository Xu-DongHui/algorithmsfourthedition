package sorting.mergesort;

/*
 * 1.�Ե����ϵĹ鲢�������Ƚ����������򣨽�ÿ��Ԫ�������һ����СΪ1�����飬Ȼ�������Ĺ鲢��Ȼ��˰˹鲢��һֱ��ȥ
 */
public class MergeBU extends Merge{
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
//		sz����������Ĵ�С��������СΪsz������鲢Ϊһ����СΪ2 * sz������
		for(int sz = 1; sz < N; sz = sz + sz) {
//			lo������������ĵ�һ��Ԫ�ص����������ϴ������еĵײ�������鲢��������
			for(int lo = 0; lo < N - sz; lo += sz + sz)
//				lo, min(lo + sz - 1), hi(lo + sz + sz -1)
//				�ܻ�����һ��lo < N -sz���������sz��������ʣ�µ����һ���ֽ��й鲢
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz -1, N - 1));
		}
	}
}
