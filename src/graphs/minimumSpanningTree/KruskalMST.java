package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/*
 * 1.Kruskal�㷨
 * ��1�����ձߵ�Ȩ�ش�С������������ѡ��Ȩ����С�ıߣ����߼�����С������������ı߲������Ѿ�����ı߹��ɻ���ֱ��������V-1����Ϊֹ��
 * ��2����V�õ�����������ɵ�ɭ�ֿ�ʼ�������ҵ�����̵ıߣ����������ϲ���ֱ��ֻʣ��һ��������������С������
 * 
 */

public class KruskalMST {
//	�洢��С�������ı�
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
//		�洢ͼ�����еı�,ȡ����СȨ�صı�
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e : G.edges())
			pq.insert(e);
		UF uf = new UF(G.V());
		while(!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
//			����������w��v�Ѿ���һ����ͨ������,����Ը�ʧЧ�ıߡ���Ϊһ�������������ߣ��ͻṹ��һ������
			if(uf.connected(v, w))
				continue;
//			����w��v����
			uf.union(v, w);
//			�洢��С�������ı�
			mst.enqueue(e);
		}
	}
	
	public Iterable<Edge> edges() {
		return mst;
	}
}
