package fundamentals.linkednode;

import java.util.Iterator;

/*
 * 1.建立基于链表的先进先出的队列
 * 2.链表是数组的一种重要的替代方式
 * 3.链表的优势
 */
public class Queue<Item> implements Iterable<Item>{
	private Node first;//指向最早添加的节点
	private Node last;//指向最近添加的节点
	private int N;//队列中的元素个数
	
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}
	public void enqueue(Item item) {
		//视角着眼于last节点，产生和连接最后一个节点
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
		//视角着眼于first节点
		Node oldFirst = first;
		first = oldFirst.next;
		if(isEmpty())//first是判断是否为空的标志，last要根据链表情况进行更新，都为null
			last = null;
		N--;
		return oldFirst.item;
	}
	
	private class Node {
		Item item;
		Node next;
	}

	//保证集合的for迭代
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
