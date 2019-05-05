package fundamentals.linkednode;

import java.util.Iterator;
/*
 * 1.����������ջ
 * 2.���Դ����������͵�����
 * 3.����ռ�Ĵ�С�ͼ��ϵĴ�С������
 * 4.���������ʱ��ͼ��ϵĴ�С�޹�
 */
public class Stack<Item> implements Iterable<Item> {
	private  Node first;//ջ��Ԫ��
	private int N;//ջ�Ĵ�С
	private class Node {
		Item item;
		Node next;
	}
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}
	public void push(Item item) {//ֻ��Ҫ����ջ���ڵ�first����
		Node oldFirst = first;
		//first��Ϊһ����ǣ���Զָ���һ���ڵ�
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	public Item pop() {//ֻ��Ҫ����ջ���ڵ�first����
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	//��֤���ϵ�for����
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
