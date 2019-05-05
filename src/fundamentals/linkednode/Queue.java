package fundamentals.linkednode;

import java.util.Iterator;

/*
 * 1.��������������Ƚ��ȳ��Ķ���
 * 2.�����������һ����Ҫ�������ʽ
 * 3.���������
 */
public class Queue<Item> implements Iterable<Item>{
	private Node first;//ָ��������ӵĽڵ�
	private Node last;//ָ�������ӵĽڵ�
	private int N;//�����е�Ԫ�ظ���
	
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}
	public void enqueue(Item item) {
		//�ӽ�������last�ڵ㣬�������������һ���ڵ�
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty())
			first = last;
		else
			oldLast.next = last;
		N++;
	}
	public Item dequeue() {
		//�ӽ�������first�ڵ�
		Node oldFirst = first;
		first = oldFirst.next;
		if(isEmpty())//first���ж��Ƿ�Ϊ�յı�־��lastҪ��������������и��£���Ϊnull
			last = null;
		N--;
		return oldFirst.item;
	}
	
	private class Node {
		Item item;
		Node next;
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
