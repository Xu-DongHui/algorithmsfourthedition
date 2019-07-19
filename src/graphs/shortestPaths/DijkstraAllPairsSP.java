package graphs.shortestPaths;

/*
 * 得到任意顶点对之间的最短路径
 */

public class DijkstraAllPairsSP {
	private DijkstraSP[] all;
	public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
		all = new DijkstraSP[G.V()];
//		以每个顶点都作为起点，查询各顶点的最短路径树
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DijkstraSP(G, v);
		}
	}
	
//	获得顶点s的最短路径树，得到s到t的最短路径
	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);
	}
	
//	得到s到t的最短距离
	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
}
