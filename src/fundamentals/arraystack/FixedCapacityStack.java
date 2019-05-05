package fundamentals.arraystack;
/*
 * 1.固定容量的泛型栈
 * 2.在java中，数组一旦创建，其大小无法改变，因此栈使用的空间只能是这个最大容量的一部分，浪费了过多的空间
 */
public class FixedCapacityStack<Item> {
	private Item[] a;
	private  int N;
	public FixedCapacityStack(int cap) {
		/*
		 * a = new Item[cap];
		 * java中不允许创建泛型数组，只能使用类型转换
		 */
		a = (Item[]) new Object[cap];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void push(Item item) {
		a[N++] = item;
	}
	public Item pop() {
		return a[--N];
	}
}
