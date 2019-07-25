package Strings.tries;

import edu.princeton.cs.algs4.Queue;

/*
 * 1.�����ַ��������ʿ�������ͨ�õĲ����㷨����Ч�Ĳ����㷨
 * 2.���ʲ����������ַ������е������ַ�������ɣ�����ʹ�ñ����Ҽ��е��ַ����в��ҡ�
 * ��1��ÿ�����ֻ������һ�����ڵ�ָ�����������˸��ڵ㣩
 * ��2��ÿ����㶼����R�����ӣ�RΪ��ĸ��Ĵ�С�����ʲ�����һ�㶼���д����Ŀ����ӡ�
 * ��3��ÿ�����Ӷ���Ӧһ���ַ���ÿ�����Ӷ�ֻ��ָ��һ�����㣬Ҳ���������Ӷ�Ӧ���ַ���Ǳ�ָ��Ľ�㡣
 * ��4��ÿ����㺬�е�ֵ����Ϊ�ջ����Ƿ��ű��е�ĳ������������ֵ��ÿ������������ֵ�����ڸü������һ����ĸ����Ӧ�Ľ���С�
 * 3.���ʲ�������������״����Ĳ������ɾ��˳���޹أ���������һ������ļ����䵥�ʲ���������Ψһ�ġ�
 * 4.���ʲ�������δ���еĲ���һ��ֻ��Ҫ�����ٵļ�����㡣
 * 5.���ʲ������ڴ�����ʱ�������Ĵ����ռ䡣
 */

public class TrieST<Value> {
	private static int R = 256;
	private Node root;
//	��̬�ֱ࣬�ӽ��м���
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
//	���Ҳ���
	public Value get(String key) {
		Node x = get(root, key, 0);
//		������Ϊ�գ������ʧ��
		if(x == null)
			return null;
		return (Value)x.val;
	}
	
//	�ӽڵ�xָ�������ӣ�������x���ַ�����Ϊd
	private Node get(Node x, String key, int d) {
//		���ҵĽ����һ���ս��
		if(x == null)
			return null;
//		��ѯ���ַ���key��ȫ���ַ������ز�ѯ���Ľ��
		if(d == key.length())
			return x;
//		���key����һ���ַ�
		char c = key.charAt(d);
//		��ѯ��һ������
		return get(x.next[c], key, d + 1);
	}
	
//	�������
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	
//	d�ǽ��x����Ӧ���ַ����ĳ���
	private Node put(Node x, String key, Value val, int d) {
//		�ڵ������β�ַ�ʱ�������˿����ӣ�˵��trie��û�ж�Ӧ��·�������Լ��½����
		if(x == null)
			x = new Node();
//		��ѯ����key��ĩβ������ֵ
		if(d == key.length()) {
			x.val = val;
			return x;
		}
//		��ѯkey����һ���ַ�
		char c = key.charAt(d);
//		����һ�������в���
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}
	
//	�������еļ�
	public Iterable<String> keys() {
		return keysWithPrefix("");
	}
	
//	����ӵ��preǰ׺�����еļ�
	public Iterable<String> keysWithPrefix(String pre) {
//		q�洢���еļ�
		Queue<String> q = new Queue<String>();
//		get���������˲��ҵ��Ķ�Ӧ�Ľ��
		collect(get(root, pre, 0), pre, q);
		return q;
	}
	
//	�ռ����еļ�
//	��ǰ�ڵ�x���Ѿ���Ӧ�ļ�pre������иýڵ���ֵ������������
	private void collect(Node x, String pre, Queue<String> q) {
//		����ѡ�����ĳ�������ӣ�˵������·�������ڣ����ֱ�ӷ���
		if(x == null)
			return;
		if(x.val != null)
			q.enqueue(pre);
//		�������п��ܵ�·�����ҵ����м�
		for(char c = 0; c < R; c++) {
//			��ǰ׺pre�Ļ����ϣ���������ַ�������ҵ����Ǻ���preǰ׺���ַ���
			collect(x.next[c], pre + c, q);
		}
			
	}
	
//	ͨ���ƥ�������еļ�
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root, "", pat, q);
		return q;
	}
	
//	���ڵ�x��Ӧ�ļ���pre
//	����Ҫ���ǳ��ȳ���ģʽ�ַ����ļ�
	private void collect(Node x, String pre, String pat, Queue<String> q) {
		int d = pre.length();
		if(x == null)
			return;
//		���ҵ��˷���ģʽ���ַ�·�����Ҹý������ֵ�������һ�������������
		if(d == pat.length() && x.val != null) {
			q.enqueue(pre);
		}
		
//		����·�ߵ��ַ�����patƥ�䣬��ֻ����һ���ַ�����һ���֣�������Ϊ������
		if(d == pat.length())
			return;
		
//		ȡģʽ����һ���ַ�
		char next = pat.charAt(d);
		
//		�������п��ܵ�·��
		for(char c = 0; c < R; c++) {
			if(next == '.' || next == c)
//				�ڷ���ģʽ���ַ����Ͻ��ŵݹ�
				collect(x.next[c], pre + c, pat, q);
		}
	}
	
//	�ǰ׺�����ҵ�s���������Ϊkey��ǰ׺
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	
//	d��x�ڵ�����Ӧ���ĳ��ȣ�Ҳ��s����һ���ַ���������length�洢���������Ϊs��ǰ׺�ļ��ĳ���
	private int search(Node x, String s, int d, int length) {
//		�ҵ��˿����ӣ����ؼ�¼�ĳ���
		if(x == null)
			return length;
//		�ҵ���һ�������洢���ĳ���
		if(x.val != null)
			length = d;
//		�����������е�s�е��ַ�����ǰ��length�������length
		if(d == s.length())
			return length;
		
//		ȡs����һ���ַ�
		char c = s.charAt(d);
//		Ѱ����һ����
		return search(x.next[c], s, d + 1, length);
	}
	
//	ɾ������
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	
//	d�ǽ��x��Ӧ�ļ��ĳ���
	private Node delete(Node x, String key, int d) {
//		�����ӽ����Ч�����û���ҵ���Ӧ��key
		if(x == null)
			return null;
//		�ҵ��˶�Ӧ��key������Ӧ����ֵɾ��
		if(d == key.length())
			x.val = null;
		else {
//			û�б�����key������������
			char c = key.charAt(d);
//			����ɾ����key value�Ľ��
			x.next[c] = delete(x.next[c], key, d + 1);
		}
		
//		���ҵ���Ӧ��key��ʼ�������ǰ�Ľ�����������Ϊ�գ����ߵ�ǰ����ֵΪ�գ����Ǽ���β�ڵ㣩��ɾ���ýڵ�
		
//		�����ǰ�ڵ���ֵ�������ý��
		if(x.val != null)
			return x;
//		��ǰ����зǿ����ӣ������ý��
		for(char c = 0; c < R; c++) {
			if(x.next[c] != null)
				return x;
		}
//		����ɾ���ý��
		return null;
	}
}
