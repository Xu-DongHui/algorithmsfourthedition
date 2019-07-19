package graphs.shortestPaths;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/*
 * 1.���·����ͨ���㷨����ͨ���㷨û��ָ���ߵķ���˳��
 * ��1����distTo[s]��ʼ��Ϊ0������distTo[]��ʼ��Ϊ�����
 * ��2������G�е�����ߣ�֪����������Ч��Ϊֹ
 * ��3�����������s�ɴ�Ķ���w����������������distTo[w]��ֵΪ��s��w�����·���ĳ��ȣ�edgeTo[w]Ϊ��·���ϵ����һ���ߡ�
 * 2.Dijkstra�㷨��distTo[]��С�ķ������㣨���������Ķ��㣩���ɲ��������У�ֱ�����еĶ��㶼�����л������еķ��������distTo[]ֵ��Ϊ�����
 * 3.Dijkstra�㷨�ܹ������Ȩ�طǸ��ļ�Ȩ����ͼ�ĵ�������·������
 */

public class DijkstraSP {
//	�Զ���Ϊ���������飬edgeTo[v]�洢���·����������v�����ĸ��ڵ�ıߡ�
	private DirectedEdge[] edgeTo;
//	�Զ���Ϊ���������飬distTo[v]�洢��s��v����֪���·���ĳ���
	private double[] distTo;
//	������Ҫ�����ɵĶ��㣬��ȷ����һ�������ɵĶ���
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
//		��㵽������ĳ�ʼ���������㵽���ɴﶥ��ľ���Ϊ���޴�
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		
		distTo[s] = 0.0;
		
		pq.insert(s, 0.0);
		while(!pq.isEmpty()) {
//			���ɾ�������Ķ���
			relax(G, pq.delMin());
		}
	}
	
//	�ɳڲ���������һ����Ƥ�������������������·��չ��������һ���߾������ڽ���Ƥ��ת�Ƶ�һ�����̵�·���ϣ��Ӷ���������Ƥ���ѹ��
//	����v������Ӷ��㣬��ΪȨ�ض��Ǵ���0�ģ����ԣ�ѡ����СȨ�صĶ��㣬�ܲ��Ϸ��������ڵ㣬
	private void relax(EdgeWeightedDigraph G, int v) {
//		�ɳڶ��㣺ÿ�ζ����ɳڲ�������ó�����ĳ������ĸ��̵�·����������ҳ�����ÿ����������·����
		for(DirectedEdge e : G.adj(v)) {
//			w������
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
//				������̵�·��
				distTo[w] = distTo[v] + e.weight();
//				��ӵ�w����Ч��
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
	
//	���·���Ὣ���ɴ�Ķ���v��distTo[v]��Ϊһ������ֵ
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
