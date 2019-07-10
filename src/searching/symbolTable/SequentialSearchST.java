package searching.symbolTable;
/*
 * 1.符号表：存储键值对，支持插入键值对和根据键查询对应的值的两种操作
 * 2.无序链表中的顺序查找
 * （1）顺序查找：在查找中顺序遍历所有的键，并使用equals()方法来寻找与被查找的键匹配的键
 */
public class SequentialSearchST<Key, Value> {
//	链表首节点
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
	
//	根据键查询相应的值
	public Value get(Key key) {
//		遍历所有的节点
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key))
				return x.val;
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		for(Node x = first; x != null; x = x.next) {
//			如果存在对应的键,则更新值
			if(key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
//		如果没有对应的键,则插入键值对,作为头节点
		first = new Node(key, val, first);
	}

}
