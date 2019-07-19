package graphs.directedgraphs;

import edu.princeton.cs.algs4.Bag;

/*
 * 1.在有向图中，边是单向的，每条边所连接的两个顶点是一个有序对，它们的邻接性是单向的。
 * 2.一条有向边由一个第一个顶点指出并指向第二个顶点。一个顶点的出度为由该顶点指出的边的总数，一个顶点的入度为指向该顶点的边的总数。
 * 3.有向路径，有向环。
 */

public class Digraph {
//	顶点数
	private final int V;
//	边数
	private int E;
//	邻接表
	private Bag<Integer>[] adj;
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
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
	
//	有向边：从v指向w
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
//	返回原有向图的一个副本，将其所有边的方向反转。
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adj(v)) 
				R.addEdge(w, v);
		}
		return R;
	}
}
