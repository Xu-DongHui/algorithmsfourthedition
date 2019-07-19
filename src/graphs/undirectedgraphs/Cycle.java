package graphs.undirectedgraphs;

/*
 * 1.利用深度优先搜素检测环：给定的图示无环图吗
 */

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G) {
		marked = new boolean[G.V()];
//		该图有多个连通子图
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s])
				dfs(G, s, s);
		}
	}
	
//	深度优先搜索：v是当前节点，u是路径的上一个节点
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w, v);
//			u也是v的邻接顶点，要被排除
			} else if(w != u)
//				除了这条遍历路径外，顶点w已经被另外一条路径给标记过了，说明一定有环
				hasCycle = true;
		}
	}
	
	public boolean hasCycle() {
		return hasCycle;
	}
}
