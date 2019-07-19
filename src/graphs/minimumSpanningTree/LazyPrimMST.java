package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/*
 * 1.Prim�㷨��ÿһ������Ϊ�����е������һ���ߣ�һ��ʼ����ֻ��һ�����㣬Ȼ����������V-1���ߣ�ÿ�����ǽ���һ���������еĶ����벻�����еĶ�����Ȩ����С�ı߼������У��зֶ���
 * 2.ά�����бߵļ��ϣ������¼������еĶ���������Ѿ������еĶ�������б߻�ʧЧ���������¼������еĶ���������������еĶ���ı߼��뼯�ϡ�
 * ��1����ʱʵ�֣���ʧЧ�ı������ڼ����У��ȵ�Ҫɾ����ʱ���ټ��ߵ���Ч�ԡ�
 * ��2����ʱʵ�֣�ֱ�ӽ�ʧЧ�ı�ɾ����
 */

public class LazyPrimMST {
//	���������Ĳ������飬�������v�����У�������ֵΪtrue;
	private boolean[] marked;
//	������С�������еı�
	private Queue<Edge> mst;
//	������бߵļ���
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
//		���0����
		visit(G, 0);
		while(!pq.isEmpty()) {
//			ȡ��Ȩ����С�ĺ��б�
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
//			���ߵ��������㶼�Ѿ������У���ñ�ʧЧ��������һ��ѭ��
			if(marked[v] && marked[w])
				continue;
//			����������ı�
			mst.enqueue(e);
//			���һ���µĶ��㣬����������ĺ��б߼������
			if(!marked[v])
				visit(G, v);
			if(!marked[w])
				visit(G, w);
		}
	}
	
//	Ϊ�����һ�����㣬�������Ϊ�ѷ��ʣ����ҽ�������������δʧЧ�ı߼������
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
//		��ѯ���еĹ����ı�
		for(Edge e : G.adj(v)) {
//			�ж��Ƿ��Ǻ��б�
			if(!marked[e.other(v)])
				pq.insert(e);
		}
	}
	
//	�������е����б�
	public Iterable<Edge> edges() {
		return mst;
	}
}
