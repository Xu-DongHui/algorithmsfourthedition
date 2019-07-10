package fundamentals.linkednode;

import java.util.Iterator;
/*
 * 1.������������bag
 * 2.���������ıȽ�
 * 	��1���������ͨ������ֱ�ӷ�������Ԫ�أ����ڳ�ʼ��ʱ����Ҫ֪��Ԫ�ص�����
 * 	��2�������ʹ�ÿռ��Ԫ������ֱ�ӳ����ȣ�����Ҫ���÷�������Ԫ��
 * 3.bag, stack, queue�����ֳ��õĿɵ����ļ�������
 */
public class Bag<Item> implements Iterable<Item> {
	private Node first;
	private int N;
	
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}
	
	public void add(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}
	
	private class Node {
		Item item;
		Node next;
	}
	
	//��֤���ϵ�for����
	//ͨ�����ַ�ʽ������bag�е�Ԫ�صı���˳������Ԫ�ص�˳����ͬ
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
