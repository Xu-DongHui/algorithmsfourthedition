package graphs.directedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/*
 * 深度优先搜索每次只会访问每个顶点一次，将访问的顶点保存在一个数据结中，有三种不同的排列顺序：
 * 1.前序：在递归调用之前将顶点加入队列
 * 2.后序：在递归调用之后将顶点加入队列
 * 3.逆后序：在递归调用之后将顶点压入栈
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
//		前序
		pre.enqueue(v);
		
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
		
//		后序
		post.enqueue(v);
//		逆后序
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
