package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

/*
 * 1.prim算法的即时实现：
 * （1）优先队列中保存每个非树顶点w的一条边，这条边是将w与树中的顶点连接起来的权重最小的那条边。
 * （2）如果顶点w不在树中，但至少有一条边和树相连，那么edgeTo[w]是将w和树连接起来的权重最小的边，distTo[v]为这条边的权重。
 * （3）这类顶点w都保存在一条索引优先队列中，索引w关联的值是edge[w]的边的权重
 * 
 */

public class PrimMST {
//	存储距离树最近的边
	private Edge[] edgeTo;
//	存储边对应的权重
	private double[] distTo;
//	标记该节点是否已经访问过
	private boolean[] marked;
//	存储到各个邻接顶点权重最小的边
	private IndexMinPQ<Double> pq;
	
	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
//		初始化为权重无限大
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<Double>(G.V());
//		用顶点0和权重0初始化pq
		distTo[0] = 0.0;
		pq.insert(0,  0.0);
		while(!pq.isEmpty()) {
//			将最近的顶点添加进树
//			delMin()删除值，返回索引
			visit(G, pq.delMin());
		}
	}
	
//	将顶点v添加进树，并更新索引优先队列中的值
	private void visit(EdgeWeightedGraph G, int v) {
//		加入树
		marked[v] = true;
//		遍历邻接边和顶点
		for(Edge e : G.adj(v)) {
			int w = e.other(v);
//			v-w失效
			if(marked[w])
				continue;
//			更新最小权重
			if(e.weight() < distTo[w]) {
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if(pq.contains(w))
					pq.change(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
//	返回最小生成树的边
	public Iterable<Edge> edges() {
		Bag<Edge> mst = new Bag<Edge>();
//		edgeTo[]存储的边从1开始，因为edgeTo[w]代表的是到w的边，存储的是最小权重的边，插入树后就不变了
//		顶点0~v-1
		for(int v = 1; v < edgeTo.length; v++) {
			mst.add(edgeTo[v]);
		}
		return mst;
	}
}
