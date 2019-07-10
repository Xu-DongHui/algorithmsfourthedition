package fundamentals.linkednode;

import java.util.Iterator;
/*
 * 1.基于链表定义了bag
 * 2.数组和链表的比较
 * 	（1）数组可以通过索引直接访问任意元素，但在初始化时就需要知道元素的数量
 * 	（2）链表的使用空间和元素数量直接成正比，但需要引用访问任意元素
 * 3.bag, stack, queue是三种常用的可迭代的集合类型
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
	
	//保证集合的for迭代
	//通过这种方式迭代，bag中的元素的遍历顺序和添加元素的顺序相同
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
