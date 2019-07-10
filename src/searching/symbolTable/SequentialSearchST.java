package searching.symbolTable;
/*
 * 1.���ű��洢��ֵ�ԣ�֧�ֲ����ֵ�Ժ͸��ݼ���ѯ��Ӧ��ֵ�����ֲ���
 * 2.���������е�˳�����
 * ��1��˳����ң��ڲ�����˳��������еļ�����ʹ��equals()������Ѱ���뱻���ҵļ�ƥ��ļ�
 */
public class SequentialSearchST<Key, Value> {
//	�����׽ڵ�
	private Node first;
	
	private class Node {
		Key key;
		Value val;
		Node next;
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
//	���ݼ���ѯ��Ӧ��ֵ
	public Value get(Key key) {
//		�������еĽڵ�
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key))
				return x.val;
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		for(Node x = first; x != null; x = x.next) {
//			������ڶ�Ӧ�ļ�,�����ֵ
			if(key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
//		���û�ж�Ӧ�ļ�,������ֵ��,��Ϊͷ�ڵ�
		first = new Node(key, val, first);
	}

}
