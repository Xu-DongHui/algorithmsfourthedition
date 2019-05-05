package fundamentals.arraystack;

import java.util.Iterator;
/*
 *1.基于数组实现的栈
 *2.定义一个能够动态调整数组大小的下压栈(last in first out)
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
	//使用泛型，可以适用于任何元素
	private Item[] a = (Item[]) new Object[1];//栈元素，最开始只有1个
	private int N = 0;//栈元素的个数
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	//将栈移动到一个大小为max的新数组
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}
	//将元素添加到栈顶
	public void push(Item item) {
		if(N == a.length)//在插入元素前进行检查，自动进行2倍扩容
			resize(2 * a.length);
		a[N++] = item;
	}
	//从栈顶删除元素
	public Item pop() {
		Item item = a[--N];
		/*
		 * 1.java的垃圾回收策略是回收无法被访问的对象
		 * 2.栈中被pop的元素，已经永远不会被访问了，但数组中的引用，仍然可以让这个元素存在，Java的垃圾回收器没办法知道这一点，除非该引用被覆盖
		 * 3.保存一个不需要的对象的引用称为游离
		 * 4.将被pop的元素的值设为null，这将覆盖无用的引用，是系统可以在用例使用完被pop后，回收它的内存
		 */
		a[N] = null;//避免了对象游离
		//在删除元素后，自动进行缩容
		if(N > 0 && N == a.length/4)//栈的使用率不会低于四分之一
			resize(a.length/2);
		return item;
	}
	
	/*
	 * 1.集合类数据类型的基本操作之一，就是能够使用java的foreach语句通过迭代遍历集合中的每个元素
	 * 2.这些集合数据类型必须实现：
	 * 	（1）iterator()方法，返回一个Iterator对象
	 * 	（2）Iterator类中必须包含两个方法：hasNext()和next()
	 * 3.使一个类可迭代，需要做
	 * 	（1）继承接口：implements Iterable<Item>,接口内有一个抽象方法iterator(),返回一个迭代器Iterator<Item>
	 * 	（2）定义迭代器
	 * 
	 */
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArrayIterator();
	}
	
	/*
	 * foreach语法的实现原理：
	 * Stack<String> collection = new Stack<String>();
	 * for(String s: collection)
	 * 	StdOut.println(s);
	 * 
	 * foreach功能与while语句实现的功能一样:
	 * Iterator<String> i = collection.iterator();
	 * while(i.hasnext()) {
	 * 	String s = i.next();
	 * 	StdOut.println(s);
	 * }
	 */
	//支持后进先出，符合栈结构的foreach迭代，迭代器定义了foreach的迭代顺序。具体原因涉及到foreach的实现代码,见上。
	private class ReverseArrayIterator implements Iterator<Item> {//私有类，可以直接访问类的实例变量
		private int i = N;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i > 0;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return a[--i];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			//尽管迭代里面有一个remove方法，但一般都为空，因为，我们希望避免在迭代中穿插能够修改数据结构的操作
		}
		
	}

}
