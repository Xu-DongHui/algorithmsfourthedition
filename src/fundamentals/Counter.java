package fundamentals;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
//定义了一个计数器
public class Counter {
//	final修饰的成员变量，必须要赋初始值，而且是只能初始化一次。
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
