package Strings.tries;

/*
 * 1.���򵥴ʲ�������ÿ����㺬��һ���ַ����������Ӻ�һ��ֵ���������ӷֱ��Ӧ�ŵ�ǰ��ĸС�ڡ����ںʹ��ڽ����ĸ�����м���
 * 2.���򵥴ʲ�����ÿ�����ֻ��3�����ӣ�����Ŀռ�ԶС�ڵ��ʲ�������
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
//		������Ϊ�գ������ʧ��
		if(x == null)
			return null;
		return (Value)x.val;
	} 
	
//	���x�ڵ��ַ���key��d���ַ�(d=0,1,2,..)�Ƚ�
	private Node get(Node x, String key, int d) {
//		���ҵĽ��Ϊ�գ������ʧ��
		if(x == null)
			return null;
//		����key�ĵ�d���ַ�
		char c = key.charAt(d);

		if(c < x.c)
//			��������Ѱ��key�ĵ�d���ַ�
			return get(x.left, key, d);
		else if(c > x.c)
//			���ҽ����Ѱ��key�ĵ�d���ַ�
			return get(x.right, key, d);
		else if(d < key.length())
//			���м��������һ���ַ�
			return get(x.mid, key, d + 1);
		else
//			���ҵ���Ӧ��key�Ľڵ�
			return x;
	}
	
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	
//	����key���뵽���ʵ�λ��
	private Node put(Node x, String key, Value val, int d) {
//		�ж�key�ĵ�d���ַ�
		char c = key.charAt(d);
//		�����ǰ����Ϊ�գ������µĽڵ�
		if(x == null) {
			x = new Node();
			x.c = c;
		}
		
		if(c < x.c)
//			����������d���ַ�
			x.left = put(x.left, key, val, d);
		else if(c > x.c)
//			���ҽڵ�����d���ַ�
			x.right = put(x.right, key, val, d);
		else if(d < key.length() - 1)
//			��ѯû�н��������м�������d+1���ַ�
			x.mid = put(x.mid, key, val, d + 1);
		else
//			��ѯ����������ֵ
			x.val = val;
		return x;
	}
}
