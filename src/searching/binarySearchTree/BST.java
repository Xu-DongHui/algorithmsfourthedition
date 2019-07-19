package searching.binarySearchTree;

import fundamentals.linkednode.Queue;

/*
 * 1.��������ÿ���ڵ�ֻ����һ�����ڵ㣨���ڵ�û�и��ڵ㣩��ÿ���ڵ������ӽڵ�����ӽڵ㡣ÿ�����Ӷ�ָ��һ���������Ӷ�������
 * 2.����������� ÿ���ڵ����һ��Comparable�ļ����������ֵ����ÿ���ڵ�ļ���������������������ڵ�ļ���С��������������ڵ�ļ���
 * 3.������ķֲ�ʽ���ȵģ����������������������ƣ����ĸ��ڵ���ǿ��������еĵ�һ���з�Ԫ��
 * 4.����һ���������ĸ߶Ⱦ��������в����������µ����ܡ�ƽ������������֤�����ۼ��Ĳ���˳�����ĸ߶ȶ������ܼ����Ķ���lgN
 */

//���ڶ���������ķ��ű�
public class BST<Key extends Comparable<Key>, Value> {
//	����������ĸ��ڵ�
	private Node root;
	
	private class Node {
//		�洢һ�Լ�ֵ��
		private Key key;
		private Value val;
//		ÿ���ڵ�������ӽڵ�
		private Node left, right;
//		�ڵ������,�������Ըýڵ�Ϊ���ڵ�������Ľڵ�����
		private int N;
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size() {
		return size(root);
	}
	
//	�õ���xΪ���ڵ�������Ľڵ���
	public int size(Node x) {
		if(x == null)
			return 0;
		else
			return x.N;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
//	����xΪ���ڵ�������в��Ҳ�����key����Ӧ��ֵ
	public Value get(Node x, Key key) {
//		�Ҳ���,�򷵻�null
		if(x == null)
			return null;
		
//		key�������
//		���ֲ���
		int cmp = key.compareTo(x.key);
//		��keyС��x�ļ���˵��key��x����������
		if(cmp < 0)
			return get(x.left, key);
//		��key����x�ļ���˵��key��x����������
		else if(cmp > 0)
			return get(x.right, key);
//		�ҵ��˶�Ӧ�ļ�����������ֵ
		else
			return x.val;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
//	��Ӽ�ֵ�Ժ󣬷��ظ��ڵ�
//	����key���ҵ����������ֵ����֮�ʹ���һ���µĽڵ����
	private Node put(Node x, Key key, Value val) {
		if(x == null)
			return new Node(key, val, 1);
//		���ַ�
		int cmp = key.compareTo(x.key);
//		˵��keyӦ�����������ǣ������µ�������
		if(cmp < 0)
			x.left = put(x.left, key, val); 
		else if(cmp > 0)
			x.right = put(x.right, key, val);
//		�ҵ���key����������ֵ
		else
			x.val = val;
//		�����������������������������¸��ڵ�x�Ľڵ���
		x.N = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	
//	��������key
	public Key max() {
		return max(root).key;
	}
	
//	����xΪ���ڵ�Ķ�������ȡ
	private Node max(Node x) {
//		���������Ϊ�գ���ڵ�x�ļ����
		if(x.right == null)
			return x;
//		��������key��������
		return max(x.right);
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node x) {
		if(x.left == null)
			return x;
		
		return min(x.left);
	}
	
//	����ȡ����ȡС��key�ļ��е����ļ�
	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
//	����xΪ���ڵ�Ķ�������ȡ
	private Node floor(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
//		����������ȡ
		if(cmp < 0)
			return floor(x.left, key);
//		����x�ڵ�
		if(cmp == 0)
			return x;
//		����������ȡ
		Node t = floor(x.right, key);
//		��������������ڶ�Ӧ�Ľڵ㣬�򷵻�x����Ϊx.key������С��key��ֵ
		if(t == null)
			return x;
//		��������������ȡ���Ľڵ�
		else
			return t;
	}
	
	public Key select(int k) {
		return select(root, k).key;
	}
	
//	����Ϊk�ļ���������������k��С�����ļ�
//	����xΪ���ڵ�Ķ������У�ѡ��k+1�����
	private Node select(Node x, int k) {
//		������Ϊ�գ��򷵻�null
		if(x == null)
			return null;
		int t = size(x.left);
//		�������Ľڵ����k��˵������Ϊk�Ľڵ���������
		if(t > k)
			return select(x.left, k);
//		��������������������������������Ϊk-t-1�Ľڵ�
		else if(t < k)
			return select(x.right, k - t - 1);
//		�ҵ��ýڵ�
		else
			return x;
	}
	
	public int rank(Key key) {
		return rank(key, root);
	}
	
//	����xΪ���ڵ�������У�����С��x.key�ļ�������
	private int rank(Key key, Node x) {
//		����Ϊ�գ���Ϊ0
		if(x == null) 
			return 0;
		int cmp = key.compareTo(x.key);
//		������������
		if(cmp < 0)
			return rank(key, x.left);
//		������������
		else if(cmp > 0)
			return rank(key, x.right) + size(x.left) + 1;
//		����ҵ���Ӧ�Ľڵ㣬�򷵻��������Ľڵ���
		else
			return size(x.left);
	}

	public void deleteMin() {
		root = deleteMin(root);
	}
	
	public Node deleteMin(Node x) {
		if(x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) +size(x.right) + 1;
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
//	�Խڵ�xΪ���ڵ������ɾ����Ϊkey�Ľڵ㣬Ȼ�󷵻��µ���
	public Node delete(Node x, Key key) {
//		����Ϊ�գ����ص���ҲΪ��
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
//		����������ɾ���ڵ㣬����ɾ���õ����������γ��µĶ�����
		if(cmp < 0)
			x.left = delete(x.left, key);
//		����������ɾ���ڵ㣬����ɾ���õ����������γ��µĶ�����
		else if(cmp > 0)
			x.right = delete(x.right, key);
//		ɾ���ڵ�x
		else {
//			������Ϊ�գ�ֻ��Ҫ����������
			if(x.left == null)
				return x.right;
			if(x.right == null)
				return x.left;
//			ɾ���ڵ�x
			Node t = x;
//			ȡ����������С�Ľڵ㣬��Ϊ�µĸ��ڵ�
			x = min(t.right);
//			ɾ���������е���С�Ľڵ�
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		
//		���½ڵ�ļ�����
		x.N = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
//	����������ȱ������ڵ���������е����еļ���Ȼ���ӡ���ڵ�ļ�������ٴ�ӡ���ڵ��������е����нڵ�ļ�
//	���ش���lo��С��hi�����ҽڵ㣬����ڶ�����
	public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
//		�ýڵ�Ϊ�գ�����
		if(x == null)
			return;

		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
//		lo�ı�x�ڵ�ļ�С��˵���������������з��������Ĵ���lo��
		if(cmplo < 0)
			keys(x.left, queue, lo, hi);
//		�����ڵ�ļ���������ʱ���������
		if(cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
//		��hi��x�ڵ�ļ���ʱ��˵���������з��������ļ�
		if(cmphi > 0)
			keys(x.right, queue, lo, hi);
	}
}
