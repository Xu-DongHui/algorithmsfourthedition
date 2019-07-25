package Strings.tries;

/*
 * 1.三向单词查找树：每个结点含有一个字符，三条链接和一个值，三条链接分别对应着当前字母小于、等于和大于结点字母的所有键。
 * 2.三向单词查找树每个结点只有3个链接，所需的空间远小于单词查找树。
 */

public class TST<Value> {
	private Node root;
	private class Node {
		char c;
		Node left, mid, right;
		Value val;
	}
	
	public Value get(String key) {
		Node x = get(root, key, 0);
//		如果结点为空，则查找失败
		if(x == null)
			return null;
		return (Value)x.val;
	} 
	
//	结点x内的字符与key的d个字符(d=0,1,2,..)比较
	private Node get(Node x, String key, int d) {
//		查找的结点为空，则查找失败
		if(x == null)
			return null;
//		查找key的第d个字符
		char c = key.charAt(d);

		if(c < x.c)
//			在左结点中寻找key的第d个字符
			return get(x.left, key, d);
		else if(c > x.c)
//			在右结点中寻找key的第d个字符
			return get(x.right, key, d);
		else if(d < key.length())
//			在中间结点查找下一个字符
			return get(x.mid, key, d + 1);
		else
//			查找到对应的key的节点
			return x;
	}
	
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	
//	根据key插入到合适的位置
	private Node put(Node x, String key, Value val, int d) {
//		判断key的第d个字符
		char c = key.charAt(d);
//		如果当前链接为空，插入新的节点
		if(x == null) {
			x = new Node();
			x.c = c;
		}
		
		if(c < x.c)
//			在左结点插入第d个字符
			x.left = put(x.left, key, val, d);
		else if(c > x.c)
//			在右节点插入第d个字符
			x.right = put(x.right, key, val, d);
		else if(d < key.length() - 1)
//			查询没有结束，在中间结点插入第d+1个字符
			x.mid = put(x.mid, key, val, d + 1);
		else
//			查询结束，插入值
			x.val = val;
		return x;
	}
}
