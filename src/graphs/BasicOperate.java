package graphs;

import edu.princeton.cs.algs4.Graph;

public class BasicOperate {
//	计算v的度数
	public static int degree(Graph G, int v) {
		int degree = 0;
		for(int w : G.adj(v))
			degree++;
		return degree;
	}
	
//	计算所有顶点的最大度数
	public static int MaxDegree(Graph G) {
		int max = 0;
		for(int v = 0; v < G.V(); v++) {
			if(degree(G, v) > max)
				max = degree(G, v);
		}
		return max;
	}
	
//	计算所有顶点的平均度数
	public static double avgDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}
	
//	计算自环的总数
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for(int v = 0; v < G.V(); v++) {
			for(int w : G.adj(v)) {
				if(w == v)
					count++;
			}
		}
//		从图的adj()方法来看，每条边都被记过两次
		return count/2;
	}
}
