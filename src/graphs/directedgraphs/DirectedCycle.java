package graphs.directedgraphs;

import edu.princeton.cs.algs4.Stack;

/*
 * 1.优先级限制下的调度问题：给定一组有先后顺序限制的任务，在满足限制条件下安排完成所有任务。
 * 2.如果一个有优先级限制的问题中存在环，那么这个问题肯定无解
 * 3.有向无环图：一幅不含有向环的有向图
 */

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
//	存放有向环中的所有顶点
	private Stack<Integer> cycle;
//	系统递归调用的栈，表示当前正在遍历的有向路径。
	private boolean[] onStack;
	
	public DirectedCycle(Digraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v])
				dfs(G, v);
		}
	}
	
	private void dfs(Digraph G, int v) {
//		在递归调用的栈内
		onStack[v] = true;
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(this.hasCycle())
				return;
//			继续递归调用
			else if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
//			w已经存在于递归调用的栈中，因此存在环
			}else if(onStack[w]) {
				cycle = new Stack<Integer>();
//				从当前顶点v开始，返回查询路径上的点，直到又碰到w
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
//				将顶点w和v放入环中
				cycle.push(w);
				cycle.push(v);
			}
		}
//		顶点v的递归调用结束，新找一条路径
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
//	返回环内顶点集合
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
