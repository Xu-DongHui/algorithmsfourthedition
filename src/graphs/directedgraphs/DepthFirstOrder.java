package graphs.directedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/*
 * �����������ÿ��ֻ�����ÿ������һ�Σ������ʵĶ��㱣����һ�����ݽ��У������ֲ�ͬ������˳��
 * 1.ǰ���ڵݹ����֮ǰ������������
 * 2.�����ڵݹ����֮�󽫶���������
 * 3.������ڵݹ����֮�󽫶���ѹ��ջ
 */

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Digraph G) {
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v])
				dfs(G, v);
		}
	}
	
	private void dfs(Digraph G, int v) {
//		ǰ��
		pre.enqueue(v);
		
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
		
//		����
		post.enqueue(v);
//		�����
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre() {
		return pre;
	}
	
	public Iterable<Integer> post() {
		return post;
	}
	
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
	
}
