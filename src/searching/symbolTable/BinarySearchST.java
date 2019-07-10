package searching.symbolTable;

import fundamentals.linkednode.Queue;

/*��������ű�
 * 1.���������еĶ��ֲ��ң���һ��ƽ�е����飬һ���洢����һ���洢ֵ����֤��������Comparable���͵ļ�����
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> {
//	�洢��������Comparable����
	private Key[] keys;
//	�洢ֵ
	private Value[] vals;
//	��ֵ�Եĸ���
	private int N;
	
//	�̶���С�����飬���Ե���Ϊ�ɱ��С
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
//	���еļ�ֵ�Ը���
	public int size() {
		return N;
	}
	
//	���Ƿ�Ϊ��
	public boolean isEmpty() {
		return N == 0;
	}
	
//	��ȡ����Ӧ��ֵ
	public Value get(Key key) {
		if(isEmpty())
			return null;
		int i = rank(key);
//		�ж��Ƿ��ҵ��˶�Ӧ��keyֵ
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}
	
//	���ֲ���
	public int rank(Key key) {
//		��������
		int lo = 0, hi = N - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			int cmp = key.compareTo(keys[mid]);
//			���ұ߽�����
			if(cmp > 0)
				lo = mid + 1;
//			����߽�����
			else if(cmp < 0)
				hi = mid - 1;
//			�ҵ���Ӧ��ֵ
			else 
				return mid;
		}
//		lo���������б�key�����С�ļ�ֵ
		return lo;
	}
	
//	����ֵ�Դ������
	public void put(Key key, Value val) {
		int i = rank(key);
//		������ڲ���ļ������¸�ֵ����
		if(i < N && key.compareTo(keys[i]) == 0) {
			vals[i] = val;
			return;
		}
		
//		�����ֵ��������key��ֵ�����ƶ�һλ���ڳ�λ�ã��������ļ�ֵ�Բ������������ʵ�λ��
		for(int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
//	��С�ļ�
	public Key min() {
		return keys[0];
	}
	
//	���ļ�
	public Key max() {
		return keys[N - 1];
	}
	
//	����Ϊk�ļ�
	public Key select(int k) {
		return keys[k];
	}
	
//	���ڵ���key����С��
	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}
	
//	����[lo,...,hi]֮������м�
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<Key>();
//		���lo�����ڱ��У���������
		for(int i = rank(lo); i < rank(hi); i++) {
			q.enqueue(keys[i]);
		}
//		���hi���ڱ��У���������
		if(contains(hi))
			q.enqueue(keys[rank(hi)]);
		return q;
	}
	
//	��key�Ƿ��ڱ���
	public boolean contains(Key key) {
		int i = rank(key);
		return i < N && key.compareTo(keys[i]) == 0;
	}
	
//	С�ڵ���key�����ļ�
	public Key floor(Key key){
		return null;
	}
	
//	�ӱ���ɾ����key
	public Key delete(Key key) {
		return null;
	}
}
