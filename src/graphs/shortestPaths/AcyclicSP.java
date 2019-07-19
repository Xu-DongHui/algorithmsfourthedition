package graphs.shortestPaths;

import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

/*
 * 1.无环加权有向图中找出最短的路径，时间复杂度o(E + V)
 * （1）将distTo[s]初始化为0，其他distTo[]元素初始化为无穷大。
 * （2）按照拓扑排序顺序放松所有顶点。
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
		
//		加权有向图获得拓扑排序
		Topological top = new Topological(G);
		
//		根据拓扑排序放松边：前面的边被放松后，后面不可能再次遇到已经被放松的边，因此等轮到某顶点时，该顶点数组存储的值已经是最优的了
		for(int v : top.order()) {
			relax(G, v);
		}
	}
	
//	放松顶点
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
