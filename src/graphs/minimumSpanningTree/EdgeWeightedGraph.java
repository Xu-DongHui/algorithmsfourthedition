package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.Bag;

/*
 * 1.加权图：每条边关联一个权重的图模型
 * 2.图的生成树：图的一棵含有所有顶点的无环连通子图
 * 3.最小生成树：一棵树的所有边的权值之和最小的生成树
 * （1）只考虑连通图
 * （2）边的权重可能是0或者是负数
 * （3）所有边的权重各不相同
 * 4.生成树的性质：
 * （1）用一条边连接树上的任意两个顶点都会产生一个新的环
 * （2）从树中删去一条边将会得到两棵独立的树
 * 5.切分定理：
 * （1）图的切分：将图的所有顶点分为两个非空且不重叠的两个集合。
 * （2）横切边：一条连接两个属于不同集合的顶点的边。
 * （3）切分定理：在一幅加权图中，给定任意的切分，它的横切边中的权重最小者必然属于图的最小生成树。（权重各不相同的前提下）
 * （4）贪心算法：使用切分定理找到最小生成树的一条边，不断重复直到找到最小生成树的所有边。
 */

public class EdgeWeightedGraph {
	private final int V;
	private int E;
//	每个顶点的邻接表，存放顶点的边
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = E;
		adj = (Bag<Edge>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(Edge e) {
//		获得边的两个顶点
		int v = e.either();
		int w = e.other(v);
//		v和w添加边，添加了两次
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	
//	返回该图的所有边
	public Iterable<Edge> edges() {
		Bag<Edge> b = new Bag<Edge>();
//		遍历每个顶点的邻接表
		for(int v = 0; v < V; v++) {
			for(Edge e : adj[v]) {
//				w > v 说明该边还没被添加过
				if(e.other(v) > v)
					b.add(e);
			}
		}
		return b;
	}
}
