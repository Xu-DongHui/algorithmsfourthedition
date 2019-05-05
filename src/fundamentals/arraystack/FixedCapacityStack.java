package fundamentals.arraystack;
/*
 * 1.�̶������ķ���ջ
 * 2.��java�У�����һ�����������С�޷��ı䣬���ջʹ�õĿռ�ֻ����������������һ���֣��˷��˹���Ŀռ�
 */
public class FixedCapacityStack<Item> {
	private Item[] a;
	private  int N;
	public FixedCapacityStack(int cap) {
		/*
		 * a = new Item[cap];
		 * java�в��������������飬ֻ��ʹ������ת��
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
