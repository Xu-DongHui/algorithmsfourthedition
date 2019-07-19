package graphs.shortestPaths;

import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

/*
 * 1.�޻���Ȩ����ͼ���ҳ���̵�·����ʱ�临�Ӷ�o(E + V)
 * ��1����distTo[s]��ʼ��Ϊ0������distTo[]Ԫ�س�ʼ��Ϊ�����
 * ��2��������������˳��������ж��㡣
 */

public class AcyclicSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public AcyclicSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
//		��Ȩ����ͼ�����������
		Topological top = new Topological(G);
		
//		��������������ɱߣ�ǰ��ı߱����ɺ󣬺��治�����ٴ������Ѿ������ɵıߣ���˵��ֵ�ĳ����ʱ���ö�������洢��ֵ�Ѿ������ŵ���
		for(int v : top.order()) {
			relax(G, v);
		}
	}
	
//	���ɶ���
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}
}
