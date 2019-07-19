package graphs.undirectedgraphs;

/*
 * 1.双色问题（这是一副二分图吗）：是否能够用两种颜色将图的所有结点着色，使得任意一条边的两个端点的颜色都不相同。
 */

public class TwoColor {
	private boolean[] marked;
//	标记顶点的颜色
	private boolean[] color;
	private boolean isTwoColorable = true;
	
	public TwoColor(Graph G) {
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s])
				dfs(G, s);
		}
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
//				将下一个节点的颜色标记成与当前节点不同的颜色
				color[w] = !color[v];
				dfs(G, w);
			} else if(color[w] == color[v])
				isTwoColorable = false;
		}
	}
	
	public boolean isBipartite() {
		return isTwoColorable;
	}
}
