package graphs.undirectedgraphs;

import edu.princeton.cs.algs4.Bag;

/*
 * 1.图是由一组顶点和一组能将两个顶点相连的边组成的
 * 2.自环：一天连接一个顶点和自身的边。
 * 3.路径：由边顺序连接的一系列顶点；简单路径：一条没有重复顶点的路径。
 * 4.环：一条至少含有一条边且起点和终点相同的路径。简单环：一条没有重复顶点和边的环。
 * 5.连通图：任意一个顶点都存在一条路径到达另一个任意顶点
 * 6.树：一幅无环连通图；森林：互不相连的树组成的集合。
 * 7.图的密度：已经连接的顶点对占所有可能的顶点对的比例；稀疏图：被连接的顶点对很少；稠密图：只有少部分顶点对没有边连接。
 * 8.二分图：能将所有结点分为两部分，其中图的每条边所连接的两个顶点都分别属于不同的部分。
 * 9.某个顶点度数：依附于这个顶点的边的总数。
 * 
 * 10.邻接表：将每个顶点的所有相邻结点都保存在该顶点对应的元素所指向的一张链表中。
 */

public class Graph {
//	顶点数目
	private final int V;
//	边的数目
	private int E;
//	邻接表
	private Bag<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
//		邻接表初始化为空
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
//	添加一条连接v和w的边
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
//	顶点v的所有邻接顶点
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
}
