package fundamentals.linkednode;

import java.util.Iterator;
/*
 * 1.基于链表建立栈
 * 2.可以处理任意类型的数据
 * 3.所需空间的大小和集合的大小成正比
 * 4.操作所需的时间和集合的大小无关
 */
public class Stack<Item> implements Iterable<Item> {
	private  Node first;//栈顶元素
	private int N;//栈的大小
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
	public void push(Item item) {//只需要操作栈顶节点first即可
		Node oldFirst = first;
		//first作为一个标记，永远指向第一个节点
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	public Item pop() {//只需要操作栈顶节点first即可
		Item item = first.item;
		first = first.next;
		N--;
		return item;
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
