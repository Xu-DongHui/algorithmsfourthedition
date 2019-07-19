package graphs.shortestPaths;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/*
 * 1.最短路径的通用算法：（通用算法没有指定边的放松顺序）
 * （1）将distTo[s]初始化为0，其他distTo[]初始化为无穷大
 * （2）放松G中的任意边，知道不存在有效边为止
 * （3）对于任意从s可达的顶点w，进行上述操作后，distTo[w]的值为从s到w的最短路径的长度，edgeTo[w]为该路径上的最后一条边。
 * 2.Dijkstra算法将distTo[]最小的非树顶点（离起点最近的顶点）放松并加入树中，直到所有的顶点都在树中或者所有的非树顶点的distTo[]值均为无穷大
 * 3.Dijkstra算法能够解决边权重非负的加权有向图的单起点最短路径问题
 */

public class DijkstraSP {
//	以顶点为索引的数组，edgeTo[v]存储最短路径树中连接v和它的父节点的边。
	private DirectedEdge[] edgeTo;
//	以顶点为索引的数组，distTo[v]存储从s到v的已知最短路径的长度
	private double[] distTo;
//	保存需要被放松的顶点，并确认下一个被放松的顶点
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
//		起点到各顶点的初始距离或者起点到不可达顶点的距离为无限大
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		
		distTo[s] = 0.0;
		
		pq.insert(s, 0.0);
		while(!pq.isEmpty()) {
//			放松距离最近的顶点
			relax(G, pq.delMin());
		}
	}
	
//	松弛操作：如用一根橡皮筋沿着连接两个顶点的路径展开，放松一条边就类似于将橡皮筋转移到一条更短的路径上，从而缓解了橡皮筋的压力
//	放松v下面的子顶点，因为权重都是大于0的，所以，选择最小权重的顶点，能不断放松其他节点，
	private void relax(EdgeWeightedDigraph G, int v) {
//		松弛顶点：每次顶点松弛操作都会得出到达某个顶点的更短的路径，最后逐渐找出到达每个顶点的最短路径。
		for(DirectedEdge e : G.adj(v)) {
//			w被放松
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
//				保存更短的路径
				distTo[w] = distTo[v] + e.weight();
//				添加到w的有效边
				edgeTo[w] = e;
				if(pq.contains(w))
					pq.change(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
//	最短路径会将起点可达的顶点v的distTo[v]设为一个有限值
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		if(!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
}
