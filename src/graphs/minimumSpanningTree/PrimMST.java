package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

/*
 * 1.prim�㷨�ļ�ʱʵ�֣�
 * ��1�����ȶ����б���ÿ����������w��һ���ߣ��������ǽ�w�����еĶ�������������Ȩ����С�������ߡ�
 * ��2���������w�������У���������һ���ߺ�����������ôedgeTo[w]�ǽ�w��������������Ȩ����С�ıߣ�distTo[v]Ϊ�����ߵ�Ȩ�ء�
 * ��3�����ඥ��w��������һ���������ȶ����У�����w������ֵ��edge[w]�ıߵ�Ȩ��
 * 
 */

public class PrimMST {
//	�洢����������ı�
	private Edge[] edgeTo;
//	�洢�߶�Ӧ��Ȩ��
	private double[] distTo;
//	��Ǹýڵ��Ƿ��Ѿ����ʹ�
	private boolean[] marked;
//	�洢�������ڽӶ���Ȩ����С�ı�
	private IndexMinPQ<Double> pq;
	
	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
//		��ʼ��ΪȨ�����޴�
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<Double>(G.V());
//		�ö���0��Ȩ��0��ʼ��pq
		distTo[0] = 0.0;
		pq.insert(0,  0.0);
		while(!pq.isEmpty()) {
//			������Ķ�����ӽ���
//			delMin()ɾ��ֵ����������
			visit(G, pq.delMin());
		}
	}
	
//	������v��ӽ������������������ȶ����е�ֵ
	private void visit(EdgeWeightedGraph G, int v) {
//		������
		marked[v] = true;
//		�����ڽӱߺͶ���
		for(Edge e : G.adj(v)) {
			int w = e.other(v);
//			v-wʧЧ
			if(marked[w])
				continue;
//			������СȨ��
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
	
//	������С�������ı�
	public Iterable<Edge> edges() {
		Bag<Edge> mst = new Bag<Edge>();
//		edgeTo[]�洢�ıߴ�1��ʼ����ΪedgeTo[w]������ǵ�w�ıߣ��洢������СȨ�صıߣ���������Ͳ�����
//		����0~v-1
		for(int v = 1; v < edgeTo.length; v++) {
			mst.add(edgeTo[v]);
		}
		return mst;
	}
}
