package searching.binarySearchTree;

import fundamentals.linkednode.Queue;

/*
 * 1.二叉树：每个节点只能有一个父节点（根节点没有父节点），每个节点有左子节点和右子节点。每个链接都指向一个独立的子二叉树。
 * 2.二叉查找树： 每个节点包含一个Comparable的键和相关联的值，且每个节点的键都大于其左子树的任意节点的键而小于右子树的任意节点的键。
 * 3.假设键的分布式均匀的，二叉查找树与快速排序类似，树的根节点就是快速排序中的第一个切分元素
 * 4.给定一棵树，树的高度决定了所有操作在最坏情况下的性能。平衡二叉查找树保证了无论键的插入顺序，数的高度都将是总键数的对数lgN
 */

//基于二叉查找树的符号表
public class BST<Key extends Comparable<Key>, Value> {
//	二叉查找树的根节点
	private Node root;
	
	private class Node {
//		存储一对键值对
		private Key key;
		private Value val;
//		每个节点的左右子节点
		private Node left, right;
//		节点计数器,给出了以该节点为根节点的子树的节点总数
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
	
//	得到以x为根节点的子树的节点数
	public int size(Node x) {
		if(x == null)
			return 0;
		else
			return x.N;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
//	在以x为根节点的子树中查找并返回key所对应的值
	public Value get(Node x, Key key) {
//		找不到,则返回null
		if(x == null)
			return null;
		
//		key是有序的
//		二分查找
		int cmp = key.compareTo(x.key);
//		当key小于x的键，说明key在x的左子树上
		if(cmp < 0)
			return get(x.left, key);
//		当key大于x的键，说明key在x的右子树上
		else if(cmp > 0)
			return get(x.right, key);
//		找到了对应的键，返回它的值
		else
			return x.val;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
//	添加键值对后，返回根节点
//	查找key，找到则更新它的值，反之就创建一个新的节点插入
	private Node put(Node x, Key key, Value val) {
		if(x == null)
			return new Node(key, val, 1);
//		二分法
		int cmp = key.compareTo(x.key);
//		说明key应该在左子树那，返回新的左子树
		if(cmp < 0)
			x.left = put(x.left, key, val); 
		else if(cmp > 0)
			x.right = put(x.right, key, val);
//		找到了key，更新它的值
		else
			x.val = val;
//		不管是在左子树还是右子树，更新根节点x的节点数
		x.N = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	
//	查找最大的key
	public Key max() {
		return max(root).key;
	}
	
//	在以x为根节点的二叉树中取
	private Node max(Node x) {
//		如果右子树为空，则节点x的键最大
		if(x.right == null)
			return x;
//		否则最大的key在右子树
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
	
//	向下取整：取小于key的键中的最大的键
	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
//	在以x为根节点的二叉树中取
	private Node floor(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
//		在左子树中取
		if(cmp < 0)
			return floor(x.left, key);
//		返回x节点
		if(cmp == 0)
			return x;
//		在右子树中取
		Node t = floor(x.right, key);
//		如果右子树不存在对应的节点，则返回x。因为x.key是最大的小于key的值
		if(t == null)
			return x;
//		返回在右子树中取到的节点
		else
			return t;
	}
	
	public Key select(int k) {
		return select(root, k).key;
	}
	
//	排名为k的键，即树中正好有k个小于它的键
//	在以x为根节点的二叉树中，选第k+1个结点
	private Node select(Node x, int k) {
//		该子树为空，则返回null
		if(x == null)
			return null;
		int t = size(x.left);
//		左子树的节点大于k，说明排名为k的节点在左子树
		if(t > k)
			return select(x.left, k);
//		否则是在右子树，找在右子树中排名为k-t-1的节点
		else if(t < k)
			return select(x.right, k - t - 1);
//		找到该节点
		else
			return x;
	}
	
	public int rank(Key key) {
		return rank(key, root);
	}
	
//	在以x为根节点的子树中，查找小于x.key的键的数量
	private int rank(Key key, Node x) {
//		子树为空，则为0
		if(x == null) 
			return 0;
		int cmp = key.compareTo(x.key);
//		在左子树中找
		if(cmp < 0)
			return rank(key, x.left);
//		在右子树中找
		else if(cmp > 0)
			return rank(key, x.right) + size(x.left) + 1;
//		如果找到对应的节点，则返回左子树的节点数
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
	
//	以节点x为根节点的树中删除键为key的节点，然后返回新的树
	public Node delete(Node x, Key key) {
//		该树为空，返回的树也为空
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
//		在左子树中删除节点，返回删除好的左子树，形成新的二叉树
		if(cmp < 0)
			x.left = delete(x.left, key);
//		在右子树中删除节点，返回删除好的右子树，形成新的二叉树
		else if(cmp > 0)
			x.right = delete(x.right, key);
//		删除节点x
		else {
//			左子树为空，只需要返回右子树
			if(x.left == null)
				return x.right;
			if(x.right == null)
				return x.left;
//			删除节点x
			Node t = x;
//			取右子树的最小的节点，作为新的根节点
			x = min(t.right);
//			删除右子树中的最小的节点
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		
//		更新节点的计数器
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
	
//	中序遍历：先遍历根节点的左子树中的所有的键，然后打印根节点的键，最后再打印根节点右子树中的所有节点的键
//	返回大于lo，小于hi的左右节点，存放在队列中
	public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
//		该节点为空，则无
		if(x == null)
			return;

		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
//		lo的比x节点的键小，说明左子树中依旧有符合条件的大于lo的
		if(cmplo < 0)
			keys(x.left, queue, lo, hi);
//		当根节点的键符合条件时，翻入队列
		if(cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
//		当hi比x节点的键大时，说明右子树有符合条件的键
		if(cmphi > 0)
			keys(x.right, queue, lo, hi);
	}
}
