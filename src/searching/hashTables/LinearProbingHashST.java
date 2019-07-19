package searching.hashTables;

/*
 * 基于线性探测法的散列表
 * 1.线性探测法：当碰撞发生时（一个键的散列值已经被另一个不同的键占用）我们可以直接检查散列表的下一个位置（将索引值加1），知道找到该键或者找到一个空元素
 * 2.N/M称为散列表的使用率，为保证性能，需要动态调整数组大小。当散列表快满时，查收所需的探测次数巨大，当使用率小于1/2时，探测次数只需要1.5~2.5之间
 */

public class LinearProbingHashST<Key, Value> {
	
//	符号表中的键值对数
	private int N;
//	线性探测表的大小
	private int M;
//	键
	private Key[] keys;
//	值
	private Value[] vals;
	
	public LinearProbingHashST() {
		this(16);
	}
	
	public LinearProbingHashST(int M) {
		this.M = M;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
//	返回key的索引
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

//	调整数组大小
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>(cap);
		for(int i = 0; i < M; i++) {
			if(keys[i] != null)
				t.put(keys[i], vals[i]);
		}
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
	
//	插入键值对
	public void put(Key key, Value val) {
		if(N >= M/2)
			resize(2 * M);
		int i;
//		查找一个键，从它的散列值开始顺序查找
//		将索引增大，达到数组结尾时，折回数组的开头
		for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
//			找到该键，更新值
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
//		没有找到该键，添加键值对
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
//	查找该键
	public Value get(Key key) {
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key))
				return vals[i];
		}
		return null;
	}
	
	private boolean contains(Key key) {
		if(get(key) != null)
			return true;
		return false;
	}
	
//	删除该键值对：删除该键，然后把该键后面的非空元素，重新添加到符号表中
	public void delete(Key key) {
		if(!contains(key))
			return;
		int i = hash(key);
//		找到该键
		while(!key.equals(keys[i])) {
			i = (i + 1) % M;
		}
		keys[i] = null;
		keys[i] = null;
		i = (i + 1) % M;
//		将键后面的其他元素添加
		while(keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N--;
		if(N > 0 && N == M/8)
			resize(M/2);
	}
}
