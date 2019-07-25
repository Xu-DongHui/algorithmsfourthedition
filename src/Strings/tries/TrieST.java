package Strings.tries;

import edu.princeton.cs.algs4.Queue;

/*
 * 1.利用字符串的性质开发出比通用的查找算法更有效的查找算法
 * 2.单词查找树：由字符串键中的所有字符构造而成，允许使用被查找键中的字符进行查找。
 * （1）每个结点只可能有一个父节点指向它。（除了根节点）
 * （2）每个结点都含有R条链接，R为字母表的大小。单词查找树一般都含有大量的空链接。
 * （3）每条链接都对应一个字符，每条链接都只能指向一个顶点，也可以用链接对应的字符标记被指向的结点。
 * （4）每个结点含有的值可以为空或者是符号表中的某个键所关联的值。每个键所关联的值保存在该键的最后一个字母所对应的结点中。
 * 3.单词查找树的链表形状与键的插入或者删除顺序无关，对于任意一组给定的键，其单词查找树都是唯一的。
 * 4.单词查找树的未命中的查找一般只需要检查很少的几个结点。
 * 5.单词查找树在处理长键时，会消耗大量空间。
 */

public class TrieST<Value> {
	private static int R = 256;
	private Node root;
//	静态类，直接进行加载
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
//	查找操作
	public Value get(String key) {
		Node x = get(root, key, 0);
//		如果结点为空，则查找失败
		if(x == null)
			return null;
		return (Value)x.val;
	}
	
//	从节点x指出的链接，到达结点x的字符个数为d
	private Node get(Node x, String key, int d) {
//		查找的结点是一个空结点
		if(x == null)
			return null;
//		查询了字符串key的全部字符，返回查询到的结点
		if(d == key.length())
			return x;
//		获得key的下一个字符
		char c = key.charAt(d);
//		查询下一个链接
		return get(x.next[c], key, d + 1);
	}
	
//	插入操作
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	
//	d是结点x所对应的字符串的长度
	private Node put(Node x, String key, Value val, int d) {
//		在到达键的尾字符时，遇到了空链接，说明trie中没有对应的路径，得自己新建结点
		if(x == null)
			x = new Node();
//		查询到了key的末尾，插入值
		if(d == key.length()) {
			x.val = val;
			return x;
		}
//		查询key的下一个字符
		char c = key.charAt(d);
//		在下一个链接中插入
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}
	
//	查找所有的键
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}
	
//	查找拥有pre前缀的所有的键
	public Iterable<String> keysWithPrefix(String pre) {
//		q存储所有的键
		Queue<String> q = new Queue<String>();
//		get方法返回了查找到的对应的结点
		collect(get(root, pre, 0), pre, q);
		return q;
	}
	
//	收集所有的键
//	当前节点x，已经对应的键pre，如果有该节点有值，就添加入队列
	private void collect(Node x, String pre, Queue<String> q) {
//		可能选择的是某条空链接，说明这条路径不存在，因此直接返回
		if(x == null)
			return;
		if(x.val != null)
			q.enqueue(pre);
//		遍历所有可能的路径，找到所有键
		for(char c = 0; c < R; c++) {
//			在前缀pre的基础上，不断添加字符，因此找到的是含有pre前缀的字符串
			collect(x.next[c], pre + c, q);
		}
			
	}
	
//	通配符匹配获得所有的键
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root, "", pat, q);
		return q;
	}
	
//	到节点x对应的键是pre
//	不需要考虑长度超过模式字符串的键
	private void collect(Node x, String pre, String pat, Queue<String> q) {
		int d = pre.length();
		if(x == null)
			return;
//		查找到了符合模式的字符路径，且该结点上有值，因此是一个键，加入队列
		if(d == pat.length() && x.val != null) {
			q.enqueue(pre);
		}
		
//		这条路线的字符串与pat匹配，但只是另一个字符串的一部分，不能作为键返回
		if(d == pat.length())
			return;
		
//		取模式的下一个字符
		char next = pat.charAt(d);
		
//		遍历所有可能的路径
		for(char c = 0; c < R; c++) {
			if(next == '.' || next == c)
//				在符合模式的字符串上接着递归
				collect(x.next[c], pre + c, pat, q);
		}
	}
	
//	最长前缀：查找到s的最长的能作为key的前缀
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	
//	d是x节点所对应键的长度，也是s的下一个字符的索引；length存储了最长的能作为s的前缀的键的长度
	private int search(Node x, String s, int d, int length) {
//		找到了空链接，返回记录的长度
		if(x == null)
			return length;
//		找到了一个键，存储它的长度
		if(x.val != null)
			length = d;
//		遍历完了所有的s中的字符，当前的length就是最长的length
		if(d == s.length())
			return length;
		
//		取s的下一个字符
		char c = s.charAt(d);
//		寻找下一个键
		return search(x.next[c], s, d + 1, length);
	}
	
//	删除操作
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	
//	d是结点x对应的键的长度
	private Node delete(Node x, String key, int d) {
//		该链接结点无效，因此没有找到对应的key
		if(x == null)
			return null;
//		找到了对应的key，将对应结点的值删除
		if(d == key.length())
			x.val = null;
		else {
//			没有遍历完key，接着往下找
			char c = key.charAt(d);
//			更新删除完key value的结点
			x.next[c] = delete(x.next[c], key, d + 1);
		}
		
//		从找到对应的key开始，如果当前的结点的所有链接为空，或者当前结点的值为空（不是键的尾节点）则删除该节点
		
//		如果当前节点有值，保留该结点
		if(x.val != null)
			return x;
//		当前结点有非空链接，保留该结点
		for(char c = 0; c < R; c++) {
			if(x.next[c] != null)
				return x;
		}
//		否则删除该结点
		return null;
	}
}
