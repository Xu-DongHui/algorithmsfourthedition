package graphs.shortestPaths;

import edu.princeton.cs.algs4.Bag;
import graphs.directedgraphs.Digraph;

/*
 * 1.最短路径：在一幅加权有向图中，从顶点s到顶点t的最短路径是所有从s到t的路径中的权重最小者。
 * 2.最短路径树：以s为起点的一棵最短路径树是图的一幅子图，它包含s和从s可达的所有顶点，这棵有向树的根节点是s，树的每条路径都是有向图中的一条最短路径。
 * 3.最小生成树和最短路径的区别：
 * （1） 最小生成树能够保证整个拓扑图的所有路径之和最小，但不能保证任意两点之间是最短路径。
 * （2）最短路径是从一点出发，到达目的地的路径最小。
 */

public class EdgeWeightedDigraph {
	private final int V;
	private int E;
//	维护一个由顶点索引的bag对象数组，bag对象存储DirectedEdge对象。
	private Bag<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
//	有向边只会出现在一个顶点的邻接表中
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for(int v = 0; v < V; v++) {
			for(DirectedEdge e : adj[v])
				bag.add(e);
		}
		return bag;
	}
}
