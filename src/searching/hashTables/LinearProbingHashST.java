package searching.hashTables;

/*
 * ��������̽�ⷨ��ɢ�б�
 * 1.����̽�ⷨ������ײ����ʱ��һ������ɢ��ֵ�Ѿ�����һ����ͬ�ļ�ռ�ã����ǿ���ֱ�Ӽ��ɢ�б����һ��λ�ã�������ֵ��1����֪���ҵ��ü������ҵ�һ����Ԫ��
 * 2.N/M��Ϊɢ�б��ʹ���ʣ�Ϊ��֤���ܣ���Ҫ��̬���������С����ɢ�б����ʱ�����������̽������޴󣬵�ʹ����С��1/2ʱ��̽�����ֻ��Ҫ1.5~2.5֮��
 */

public class LinearProbingHashST<Key, Value> {
	
//	���ű��еļ�ֵ����
	private int N;
//	����̽���Ĵ�С
	private int M;
//	��
	private Key[] keys;
//	ֵ
	private Value[] vals;
	
	public LinearProbingHashST() {
		this(16);
	}
	
	public LinearProbingHashST(int M) {
		this.M = M;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
//	����key������
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

//	���������С
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
	
//	�����ֵ��
	public void put(Key key, Value val) {
		if(N >= M/2)
			resize(2 * M);
		int i;
//		����һ������������ɢ��ֵ��ʼ˳�����
//		���������󣬴ﵽ�����βʱ���ۻ�����Ŀ�ͷ
		for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
//			�ҵ��ü�������ֵ
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
//		û���ҵ��ü�����Ӽ�ֵ��
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
//	���Ҹü�
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
	
//	ɾ���ü�ֵ�ԣ�ɾ���ü���Ȼ��Ѹü�����ķǿ�Ԫ�أ�������ӵ����ű���
	public void delete(Key key) {
		if(!contains(key))
			return;
		int i = hash(key);
//		�ҵ��ü�
		while(!key.equals(keys[i])) {
			i = (i + 1) % M;
		}
		keys[i] = null;
		keys[i] = null;
		i = (i + 1) % M;
//		�������������Ԫ�����
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
