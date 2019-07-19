package graphs.directedgraphs;

/*
 * 顶点的可达性：是否存在一条从一个给定顶点v到另一个给定顶点的w的路径
 */

public class TransitiveClosure {
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph G) {
		all = new DirectedDFS[G.V()];
//		记录每个顶点的可达顶点
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DirectedDFS(G, v);
		}
	}
	
//	w是从v可达的吗
	public boolean reachable(int v, int w) {
		return all[v].marked(w);
	}
}
