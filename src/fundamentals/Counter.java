package fundamentals;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
//������һ��������
public class Counter {
//	final���εĳ�Ա����������Ҫ����ʼֵ��������ֻ�ܳ�ʼ��һ�Ρ�
	private final String name;
	private int count;
	public Counter(String name) {
		this.name = name;
	}
	public void increment() {
		count++;
	}
	public int totally() {
		return count;
	}
	public String toString() {
		return count + " " + name;
	}
	
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		Counter heads = new Counter("heads");
		Counter tails = new Counter("tails");
		for(int i = 0; i < T; i++) {
			if(StdRandom.bernoulli(0.5)) 
				heads.increment();
			else
				tails.increment();
		}
		StdOut.println(heads);
		StdOut.println(tails);
		int d = heads.totally() - tails.totally();
		StdOut.println("delta: " + Math.abs(d));
	}
}
