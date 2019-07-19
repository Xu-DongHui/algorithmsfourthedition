package graphs.shortestPaths;

import edu.princeton.cs.algs4.Queue;

/*
 * 1.һ���Ȩ����ͼ�е����·������
 * ��1�����ܼ��л����и�Ȩ�صıߵļ�Ȩ����ͼ�����·���㷨
 * ��2����Ȩ�ػ����������бߵ�Ȩ��֮��Ϊ�������򻷡����ڸ�Ȩ�ػ�ʱû�����·������Ϊ�����������Ȩ�ػ���������С��·��
 * 2.Bellman-Ford�㷨��
 * ��1�������⺬��V������ļ�Ȩ����ͼ�и������s����s�޷������κθ�Ȩ�ػ���
 * ��2����distTo[s]��ʼ��Ϊ0������distTo[]Ԫ�س�ʼ��Ϊ�����
 * ��3��������˳���������ͼ�����бߣ��ظ�V��
 * 3.���ڶ��е�Bellman-Ford�㷨��ʹ��FIFO���д����һ����distTo[]ֵ�����仯�Ķ��㡣
 */
public class BellmanFordSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
//	�������������飬ָʾ�����Ƿ��Ѿ�����ڶ�����
	private boolean[] onQ;
//	��ż��������ɵĶ���
	private Queue<Integer> queue;
//	relax���õĴ���
	private int cost;
//	edgeTo[]�еĸ�Ȩ�ػ�
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
//		��s������У�����ѭ����ÿ�ζ��Ӷ�����ȡ��һ���������������
		queue.enqueue(s);
		onQ[s] = true;
		
		while(!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.dequeue();
			onQ[v] = false;
//			���ɶ���v
			relax(G, v);
		}
		
	}
	
//	���ɶ���v������v����һ������w�����distTo[w]�����ˣ��ͽ�w��������У���Ϊ����Ӱ�����ȥ�Ķ���·��
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
//			���ɱߣ����Ը���ֵ�����Է���ʧЧ�ıߣ�������ֵ
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(!onQ[w]) {
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
//			����V�����Ѿ��������ˣ������Լ��edgeTo[]���Ƿ��и�Ȩ��
			if(cost++ % G.V() == 0)
				findNegativeCycle();
		}
	}
	
	public boolean hasNegativeCycle() {
		return false;
	}
	
//	��Ȩ�ػ��ļ��
	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
//		��edgeTo[]ת��Ϊ��Ȩ����ͼ
		for(int v = 0; v < V; v++) {
			if(edgeTo[v] != null) 
				spt.addEdge(edgeTo[v]);
		}
//		�жϼ�Ȩ����ͼ���Ƿ��л���
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
