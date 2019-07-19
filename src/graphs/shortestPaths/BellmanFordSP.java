package graphs.shortestPaths;

import edu.princeton.cs.algs4.Queue;

/*
 * 1.一般加权有向图中的最短路径问题
 * （1）可能既有环又有负权重的边的加权有向图的最短路径算法
 * （2）负权重环：环上所有边的权重之和为负的有向环。存在负权重环时没有最短路径，因为可以用这个负权重环构造任意小的路径
 * 2.Bellman-Ford算法：
 * （1）在任意含有V个顶点的加权有向图中给定起点s，从s无法到达任何负权重环。
 * （2）将distTo[s]初始化为0，其他distTo[]元素初始化为无穷大
 * （3）以任意顺序放松有向图的所有边，重复V轮
 * 3.基于队列的Bellman-Ford算法：使用FIFO队列存放上一轮中distTo[]值发生变化的顶点。
 */
public class BellmanFordSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
//	顶点索引的数组，指示顶点是否已经存放在队列中
	private boolean[] onQ;
//	存放即将被放松的顶点
	private Queue<Integer> queue;
//	relax调用的次数
	private int cost;
//	edgeTo[]中的负权重环
	private Iterable<DirectedEdge> cycle;
	
	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQ = new boolean[G.V()];
		queue = new Queue<Integer>();
		
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[0] = 0.0;
//		将s放入队列，进入循环，每次都从队列中取出一个顶点来将其放松
		queue.enqueue(s);
		onQ[s] = true;
		
		while(!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.dequeue();
			onQ[v] = false;
//			放松顶点v
			relax(G, v);
		}
		
	}
	
//	放松顶点v：更新v的下一个顶点w，如果distTo[w]更新了，就将w放入队列中，因为它会影响接下去的顶点路径
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
//			放松边：可以更新值；可以放松失效的边，不更新值
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(!onQ[w]) {
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
//			代表V条边已经放松完了，周期性检查edgeTo[]中是否含有负权重
			if(cost++ % G.V() == 0)
				findNegativeCycle();
		}
	}
	
	public boolean hasNegativeCycle() {
		return false;
	}
	
//	负权重环的检测
	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
//		将edgeTo[]转换为加权有向图
		for(int v = 0; v < V; v++) {
			if(edgeTo[v] != null) 
				spt.addEdge(edgeTo[v]);
		}
//		判断加权有向图中是否含有环。
		EdgeWeightedCycleFinder cf = new EdgeWeightedCycleFinder(spt);
		cycle = cf.cycle();
	}

	private class EdgeWeightedCycleFinder {
		public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
		}
		
		public Iterable<DirectedEdge> cycle() {
			return null;
		}
	}
	
}
