package searching.balancedSearchTree;

/*��֤��������ƽ��
 * 1.2-3��������һ��2-3�������������½�㣺
 * ��1��2-��㣺����һ�����������Ӧ��ֵ�����������ӣ�������ָ���2-3���еļ���С�ڸý�㣬������ָ���2-3���еļ������ڸý�㡣
 * ��2��3-��㣺�����������������Ӧ��ֵ�����������ӣ�������ָ���2-3���еļ���С�ڸý�㣬������ָ���2-3���еļ���λ�ڸý���������֮�䣬������ָ���2-3���еļ������ڸý�㡣
 * 2.����һ������
 * ��1����2-����в��룺����2-����滻��3-���
 * ��2����3-�����룺����ʱ��������3-�����4-��㣬�ٽ���ת��Ϊ3��2-�����ɵ�2-3�����м���Ϊ����㣬�����Ϊ���������Ҽ���Ϊ��������
 * 		1������������2-��㣬���Խ��м��ƶ���������С�
 * 		2������������3-�ڵ㣬���Խ��м��ƶ���������ٴι���һ����ʱ��4-��㣬Ȼ�������м��������ĸ���㣬�Դ����ơ�
 * 		3������Ӳ����㵽����㶼��3-��㣬���ո��ڵ����һ����ʱ��4-��㡣���Խ���ʱ��4-���ֽ��3��2-�ڵ㣬ʹ���߼�1��
 * ��3��2-3�������㷨�Ĺؼ�������Щ�仯���Ǿֲ��ģ�������صĽ��������⣬�����޸Ļ��߼�������������֡�
 * ��4����Щ�ֲ��任����Ӱ������ȫ����Ч�����Ժ�ƽ���ԣ���������ӵ�������·�����ȶ�����ȵġ�
 * 3.��һ����СΪN��2-3���У����ҺͲ���������ʵĽ���Ȼ������lgN����
 * 
 * 4.ʹ�ú�ڶ��������ʵ��2-3��
 * ��1�����ӣ������ӽ�����2-�������һ��3-��㣬����3-�������һ����б�ĺ���������������2-��㣻��������2-3���е���ͨ���ӡ�
 * ��2�������Ӿ�Ϊ������
 * 5.��ת������ת������ɫ��������ת��Ϊ�����ӣ�����ת������ɫ��������ת��Ϊ�����ӡ�
 * 6.���룺�²���Ľ�����ú����������ĸ����������
 * ��1���򵥸�2-�������¼���
 * 		1������¼�С���ϼ���ֻ���������һ����ɫ��㣻
 * 		2������¼������ϼ�����Ҫ���ұ�����һ����ɫ��㣬Ȼ������תΪ��ɫ�����ӡ�
 * ��2���򵥸�3-�������¼�
 * 		1������¼�����ԭ���е����������½�㽫�����ӵ������ӣ���ʱ�����Ϊ�м�ļ����������Ӿ�Ϊ�����ӡ�������ɫת����
 * 		2������¼�С��ԭ���е����������½�㽫�����ӵ������ӣ���ʱ���������������ĺ����ӡ��Ƚ��ϲ�����ӽ�������ת�����ɵõ���һ�������
 * 		3������¼�����ԭ���е�������֮�䣬�����һ�������ӣ�һ���������ӡ��ֽ������ӽ�������ת�����ɵõ��ڶ��������
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
//		����Ӹ����ָ���Լ������ӵ���ɫ����ɫΪtrue,��ɫΪfalse
		boolean color;
		
		public Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
//	�жϽ��x�븸�ڵ�֮�����ӵ���ɫ
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
	
//	����ת������ɫ��������ת��Ϊ������
//	h�����������Ǻ�ɫ�ģ���Ҫ��������ת��������һ������ͬһ��������������������Ǻ�ɫ�ĸ���������
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
	
//	����ת
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
	
//	��ɫת����ת��һ������������ɫ�ӽ�����ɫ��
//	���ӽ�����ɫ��ڣ���������ɫ���
	private void flipColor(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
//	�������
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	
//	
	public Node put(Node h, Key key, Value val) {
//		�½�һ�������㣬�������Ϊ��ɫ
		if(h == null)
			return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
//		��Ӧ������߲��룬����󣬷����������ĸ��ڵ�
		if(cmp < 0)
			h.left = put(h.left, key, val);
//		��Ӧ�����ұ߲���
		else if(cmp > 0)
			h.right = put(h.right, key, val);
//		���¼�ֵ
		else
			h.val = val;
		
//		��ʱ������½����Ҫ����һЩ�жϱ任
//		1.������Ϊ��ɫ��������ת
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
//		2.�������������Ӷ��Ǻ�ɫ��������ת
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
//		3.�������Ӷ��Ǻ�ɫ���������ɫת�������������򸸽�㴫��
		if(isRed(h.left) && isRed(h.right))
			flipColor(h);
		
//		���µ�ǰ�����Ľ������
		h.N = 1 + size(h.left) + size(h.right);
		
		return h;
	}
	
}
