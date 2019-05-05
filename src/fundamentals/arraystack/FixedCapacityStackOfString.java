package fundamentals.arraystack;
/*
 * 1.编写了固定数组大小，固定元素类型的栈
 */
public class FixedCapacityStackOfString {
	private String[] a;//存储栈
	private int N;//记录栈元素的数量
	//设置固定的栈大小
	public FixedCapacityStackOfString(int cap) {
		a = new String[cap];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void push(String item) {
		a[N++] = item;
	}
	public String pop() {
		return a[--N];
	}
}
