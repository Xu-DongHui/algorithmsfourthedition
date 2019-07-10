package searching.symbolTable;

import fundamentals.linkednode.Queue;

/*【有序符号表】
 * 1.有序数组中的二分查找，有一对平行的数组，一个存储键，一个存储值，保证了数组中Comparable类型的键有序。
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> {
//	存储键，键是Comparable类型
	private Key[] keys;
//	存储值
	private Value[] vals;
//	键值对的个数
	private int N;
	
//	固定大小的数组，可以调整为可变大小
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
//	表中的键值对个数
	public int size() {
		return N;
	}
	
//	表是否为空
	public boolean isEmpty() {
		return N == 0;
	}
	
//	获取键对应的值
	public Value get(Key key) {
		if(isEmpty())
			return null;
		int i = rank(key);
//		判断是否找到了对应的key值
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}
	
//	二分查找
	public int rank(Key key) {
//		数组索引
		int lo = 0, hi = N - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			int cmp = key.compareTo(keys[mid]);
//			在右边接着找
			if(cmp > 0)
				lo = mid + 1;
//			在左边接着找
			else if(cmp < 0)
				hi = mid - 1;
//			找到对应的值
			else 
				return mid;
		}
//		lo代表数组中比key大的最小的键值
		return lo;
	}
	
//	将键值对存入表中
	public void put(Key key, Value val) {
		int i = rank(key);
//		如果存在插入的键，重新赋值即可
		if(i < N && key.compareTo(keys[i]) == 0) {
			vals[i] = val;
			return;
		}
		
//		插入键值，将大于key的值往后移动一位，腾出位置，将给定的键值对插入各自数组合适的位置
		for(int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
//	最小的键
	public Key min() {
		return keys[0];
	}
	
//	最大的键
	public Key max() {
		return keys[N - 1];
	}
	
//	排名为k的键
	public Key select(int k) {
		return keys[k];
	}
	
//	大于等于key的最小键
	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}
	
//	返回[lo,...,hi]之间的所有键
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<Key>();
//		如果lo存在在表中，则插入队列
		for(int i = rank(lo); i < rank(hi); i++) {
			q.enqueue(keys[i]);
		}
//		如果hi存在表中，则插入队列
		if(contains(hi))
			q.enqueue(keys[rank(hi)]);
		return q;
	}
	
//	键key是否在表中
	public boolean contains(Key key) {
		int i = rank(key);
		return i < N && key.compareTo(keys[i]) == 0;
	}
	
//	小于等于key的最大的键
	public Key floor(Key key){
		return null;
	}
	
//	从表中删除键key
	public Key delete(Key key) {
		return null;
	}
}
