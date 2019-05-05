package fundamentals.arraystack;
/*
 * 1.��д�˹̶������С���̶�Ԫ�����͵�ջ
 */
public class FixedCapacityStackOfString {
	private String[] a;//�洢ջ
	private int N;//��¼ջԪ�ص�����
	//���ù̶���ջ��С
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
