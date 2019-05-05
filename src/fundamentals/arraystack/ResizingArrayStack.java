package fundamentals.arraystack;

import java.util.Iterator;
/*
 *1.��������ʵ�ֵ�ջ
 *2.����һ���ܹ���̬���������С����ѹջ(last in first out)
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
	//ʹ�÷��ͣ������������κ�Ԫ��
	private Item[] a = (Item[]) new Object[1];//ջԪ�أ��ʼֻ��1��
	private int N = 0;//ջԪ�صĸ���
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	//��ջ�ƶ���һ����СΪmax��������
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}
	//��Ԫ����ӵ�ջ��
	public void push(Item item) {
		if(N == a.length)//�ڲ���Ԫ��ǰ���м�飬�Զ�����2������
			resize(2 * a.length);
		a[N++] = item;
	}
	//��ջ��ɾ��Ԫ��
	public Item pop() {
		Item item = a[--N];
		/*
		 * 1.java���������ղ����ǻ����޷������ʵĶ���
		 * 2.ջ�б�pop��Ԫ�أ��Ѿ���Զ���ᱻ�����ˣ��������е����ã���Ȼ���������Ԫ�ش��ڣ�Java������������û�취֪����һ�㣬���Ǹ����ñ�����
		 * 3.����һ������Ҫ�Ķ�������ó�Ϊ����
		 * 4.����pop��Ԫ�ص�ֵ��Ϊnull���⽫�������õ����ã���ϵͳ����������ʹ���걻pop�󣬻��������ڴ�
		 */
		a[N] = null;//�����˶�������
		//��ɾ��Ԫ�غ��Զ���������
		if(N > 0 && N == a.length/4)//ջ��ʹ���ʲ�������ķ�֮һ
			resize(a.length/2);
		return item;
	}
	
	/*
	 * 1.�������������͵Ļ�������֮һ�������ܹ�ʹ��java��foreach���ͨ���������������е�ÿ��Ԫ��
	 * 2.��Щ�����������ͱ���ʵ�֣�
	 * 	��1��iterator()����������һ��Iterator����
	 * 	��2��Iterator���б����������������hasNext()��next()
	 * 3.ʹһ����ɵ�������Ҫ��
	 * 	��1���̳нӿڣ�implements Iterable<Item>,�ӿ�����һ�����󷽷�iterator(),����һ��������Iterator<Item>
	 * 	��2�����������
	 * 
	 */
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArrayIterator();
	}
	
	/*
	 * foreach�﷨��ʵ��ԭ��
	 * Stack<String> collection = new Stack<String>();
	 * for(String s: collection)
	 * 	StdOut.println(s);
	 * 
	 * foreach������while���ʵ�ֵĹ���һ��:
	 * Iterator<String> i = collection.iterator();
	 * while(i.hasnext()) {
	 * 	String s = i.next();
	 * 	StdOut.println(s);
	 * }
	 */
	//֧�ֺ���ȳ�������ջ�ṹ��foreach������������������foreach�ĵ���˳�򡣾���ԭ���漰��foreach��ʵ�ִ���,���ϡ�
	private class ReverseArrayIterator implements Iterator<Item> {//˽���࣬����ֱ�ӷ������ʵ������
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
			//���ܵ���������һ��remove��������һ�㶼Ϊ�գ���Ϊ������ϣ�������ڵ����д����ܹ��޸����ݽṹ�Ĳ���
		}
		
	}

}
