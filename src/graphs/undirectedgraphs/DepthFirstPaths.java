package graphs.undirectedgraphs;

import edu.princeton.cs.algs4.Stack;

/*
 * 1.深度优先搜索能够处理很多和图有关的任务，如连通性问题
 * 2.连通性问题：两个给定顶点是否连通
 */

public class DepthFirstPaths {
//	标记以s为起点的连通变量中的节点
	private boolean[] marked;
//	存储每个与s连通的顶点回到s的路径
//	以每个顶点为索引，数组值为到该顶点（索引）的上一个顶点
//	一棵以起点s为根结点的树，含有所有与s连通的顶点
	private int[] edgeTo;
//	起始顶点
	private final int s;
	
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
//				v可以到w：存储了v到w的路径，因为每个顶点只能访问一次，因此，只会存储一个点
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	private boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
//		从终点开始，通过栈来存储，不断得到路径的上一个节点，直到得到起始节点
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
