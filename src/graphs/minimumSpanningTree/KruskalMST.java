package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/*
 * 1.Kruskal算法
 * （1）按照边的权重从小到大来处理，挑选出权重最小的边，将边加入最小生成树，加入的边不会与已经加入的边构成环，直到树中有V-1条边为止。
 * （2）由V棵单顶点的树构成的森林开始不断用找到的最短的边，将两棵树合并，直到只剩下一棵树，它就是最小生成树
 * 
 */

public class KruskalMST {
//	存储最小生成树的边
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
//		存储图的所有的边,取出最小权重的边
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e : G.edges())
			pq.insert(e);
		UF uf = new UF(G.V());
		while(!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
//			当两个顶点w和v已经在一个连通分量中,则忽略该失效的边。因为一旦再连上这条边，就会构成一个环。
			if(uf.connected(v, w))
				continue;
//			连接w和v顶点
			uf.union(v, w);
//			存储最小生成树的边
			mst.enqueue(e);
		}
	}
	
	public Iterable<Edge> edges() {
		return mst;
	}
}
