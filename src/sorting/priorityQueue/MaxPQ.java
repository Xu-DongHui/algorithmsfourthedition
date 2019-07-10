package sorting.priorityQueue;

/*
 * 1.���ȶ��У��ܹ�����Ԫ�غ�ɾ�����Ԫ��
 * 2.�ѣ�
 * ��1��������һ�ö�������ÿ����㶼���ڵ������������ӽ�㡣
 * ��2����ȫ����������ֻ��������ܱ�ʾ�����ڵ�λ����1�������ӽ����λ��2,3�����ӽڵ���ӽڵ�ֱ���λ��4,5,6,7
 * ��3������ѣ���һ���ܹ��ö��������ȫ�����������Ԫ�ء�
 * ��4����һ�����У�λ��k�Ľڵ�ĸ��ڵ��λ��Ϊ[k/2],�����������ӽڵ�λ�÷ֱ���2k��2k+1
 * 3.�ѵ�����
 * ��1���������ϵĶ����򻯣��ϸ���������ѵ�����״̬��Ϊĳ���ڵ��ñȸ��ڵ������Ҫͨ�������������ĸ��ڵ����޸��ѡ�
 * ��2���������µĶ����򻯣��³�����...���ڵ��ñ��ӽڵ�С...
 */

public class MaxPQ <Key extends Comparable<Key>> {
//	��Ŷ�Ԫ�ص����飬���ڶѵ���ȫ������
	private Key[] pq;
//	Ԫ�ظ������洢��a[1,..,N]�У�pq[0]û��ʹ��
	private int N = 0;
	
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN + 1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
//	����Ԫ�أ�����Ԫ�ز��뵽����ĩβ�����ӶѵĴ�С��������Ԫ���ϸ������ʵ�λ��
	public void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	
//	�����鶥��ɾȥ����Ԫ�أ��������һ��Ԫ�طŵ����ˣ���С�ѵĴ�С���������Ԫ���³������ʵ�λ��
	public Key delMax() {
//		���ڵ�Ϊ���Ԫ��
		Key max = pq[1];
		exch(1, N--);
//		��ֹ��������
		pq[N + 1] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
//	�ϸ�
	private void swim(int k) {
//		����ӽڵ�ȸ��ڵ���򽻻����ڵ���ӽڵ㣬�������Ͻ���
		while(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
//	�³�
	private void sink(int k) {
//		������ڵ���ӽڵ�С���򽫸��ڵ����ӽڵ������Ľڵ㽻�����������½���
		while(2 * k <= N) {
//			�ӽڵ�
			int j = 2 * k;
//			ѡȡ�����ӽڵ��и����ֵ
			if(j < N && less(j, j + 1))
				j++;
			if(!less(k, j))
				break;
//			�������ӽڵ�
			exch(k, j);
//			��������
			k = j;
		}
	}

}

/*���ڶѽṹʵ�ֶ�����
 public static void sort(Comparable[] a) {
	int N = a.length;

//	��������ѣ�Ҷ�ڵ�ĸ�����N/2;
	for(int k = N/2; k >= 1; k--) {
		sink(a, k, N);
	}
	
//	�������򣬽����ڵ����������ĩβ���ٽ��жѵ�����
	while(N > 1) {
		exch(a, 1, N--);
		sink(a, 1, N);
	}
}
 */

