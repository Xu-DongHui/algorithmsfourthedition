package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/*
 * 1.Prim算法：每一步都会为生长中的树添加一条边，一开始树中只有一个顶点，然后会向它添加V-1条边，每次总是将下一条连接树中的顶点与不在树中的顶点且权重最小的边加入树中（切分定理）
 * 2.维护横切边的集合：连接新加入树中的顶点和其他已经在树中的顶点的所有边会失效；将连接新加入树中的顶点和其他不在树中的顶点的边加入集合。
 * （1）延时实现：将失效的边先留在集合中，等到要删除的时候再检查边的有效性。
 * （2）即时实现：直接将失效的边删除。
 */

public class LazyPrimMST {
//	顶点索引的布尔数组，如果顶点v在树中，则数组值为true;
	private boolean[] marked;
//	保存最小生成树中的边
	private Queue<Edge> mst;
//	保存横切边的集合
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
//		添加0顶点
		visit(G, 0);
		while(!pq.isEmpty()) {
//			取出权重最小的横切边
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
//			当边的两个顶点都已经在树中，则该边失效，进入下一个循环
			if(marked[v] && marked[w])
				continue;
//			否则添加树的边
			mst.enqueue(e);
//			添加一个新的顶点，并将其关联的横切边加入队列
			if(!marked[v])
				visit(G, v);
			if(!marked[w])
				visit(G, w);
		}
	}
	
//	为树添加一个顶点，将它标记为已访问，并且将它关联的所有未失效的边加入队列
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
//		查询所有的关联的边
		for(Edge e : G.adj(v)) {
//			判断是否是横切边
			if(!marked[e.other(v)])
				pq.insert(e);
		}
	}
	
//	返回树中的所有边
	public Iterable<Edge> edges() {
		return mst;
	}
}
