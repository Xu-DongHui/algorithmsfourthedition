package searching.balancedSearchTree;

/*保证树的完美平衡
 * 1.2-3查找树：一棵2-3查找树包含以下结点：
 * （1）2-结点：含有一个键（及其对应的值）和两条链接，左链接指向的2-3树中的键都小于该结点，右链接指向的2-3树中的键都大于该结点。
 * （2）3-结点：含有两个键（及其对应的值）和三条链接，左链接指向的2-3树中的键都小于该结点，中链接指向的2-3树中的键都位于该结点的两个键之间，右链接指向的2-3树中的键都大于该结点。
 * 2.插入一个键：
 * （1）在2-结点中插入：将该2-结点替换成3-结点
 * （2）在3-结点插入：先临时将键存入3-结点变成4-结点，再将其转换为3个2-结点组成的2-3树，中键作为根结点，左键作为左子树，右键作为右子树。
 * 		1）如果父结点是2-结点，可以将中键移动至父结点中。
 * 		2）如果父结点是3-节点，可以将中键移动至父结点再次构造一个临时的4-结点，然后将它的中键插入它的父结点，以此类推。
 * 		3）如果从插入结点到根结点都是3-结点，最终根节点会变成一个临时的4-结点。可以将临时的4-结点分解成3个2-节点，使树高加1。
 * （3）2-3树插入算法的关键在于这些变化都是局部的，除了相关的结点和链接外，不必修改或者检查树的其他部分。
 * （4）这些局部变换不会影响树的全局有效有序性和平衡性：任意空链接到根结点的路径长度都是相等的。
 * 3.在一个大小为N的2-3树中，查找和插入操作访问的结点必然不超过lgN个。
 * 
 * 4.使用红黑二叉查找树实现2-3树
 * （1）链接：红链接将两个2-结点连成一个3-结点，即，3-结点是由一条左斜的红链接相连的两个2-结点；黑链接是2-3树中的普通链接。
 * （2）红链接均为左链接
 * 5.旋转：左旋转：将红色的右链接转化为左链接；右旋转：将红色的左链接转化为右链接。
 * 6.插入：新插入的结点是用红链接与它的父结点相连。
 * （1）向单个2-结点插入新键：
 * 		1）如果新键小于老键，只需左边新增一个红色结点；
 * 		2）如果新键大于老键，需要在右边新增一个红色结点，然后左旋转为红色左链接。
 * （2）向单个3-结点插入新键
 * 		1）如果新键大于原树中的两个键，新结点将被连接到右链接，此时根结点为中间的键，左右链接均为红链接。进行颜色转换。
 * 		2）如果新键小于原树中的两个键，新结点将被连接到左链接，此时产生了两条连续的红链接。先将上层红链接进行右旋转，即可得到第一种情况。
 * 		3）如果新键介于原树中的两个键之间，会产生一条红链接，一条右右链接。现将右链接进行左旋转，即可得到第二种情况。
 * 
 */

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	private class Node {
		Key key;
		Value val;
		Node left, right;
		int N;
//		保存从父结点指向自己的链接的颜色：红色为true,黑色为false
		boolean color;
		
		public Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
//	判断结点x与父节点之间链接的颜色
	private boolean isRed(Node x) {
		if(x == null)
			return false;
		return x.color == RED;
	}
	
	private int size(Node x) {
		if(x == null)
			return 0;
		return x.N;
	}
	
//	左旋转：将红色的右链接转化为左链接
//	h结点的右链接是红色的，需要进行左旋转，并返回一个包含同一组键的子树且其左链接是红色的根结点的链接
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		
		return x;
	}
	
//	右旋转
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
//	颜色转换：转换一个结点的两个红色子结点的颜色。
//	将子结点的颜色变黑，根结点的颜色变红
	private void flipColor(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
//	插入操作
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	
//	
	public Node put(Node h, Key key, Value val) {
//		新建一个插入结点，结点链接为红色
		if(h == null)
			return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
//		键应该在左边插入，插完后，返回左子树的根节点
		if(cmp < 0)
			h.left = put(h.left, key, val);
//		键应该在右边插入
		else if(cmp > 0)
			h.right = put(h.right, key, val);
//		更新键值
		else
			h.val = val;
		
//		临时插入的新结点需要进行一些判断变换
//		1.右链接为红色，则左旋转
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
//		2.连续两条左链接都是红色，则右旋转
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
//		3.左右链接都是红色，则进行颜色转换，将红链接向父结点传递
		if(isRed(h.left) && isRed(h.right))
			flipColor(h);
		
//		更新当前子树的结点数量
		h.N = 1 + size(h.left) + size(h.right);
		
		return h;
	}
	
}
