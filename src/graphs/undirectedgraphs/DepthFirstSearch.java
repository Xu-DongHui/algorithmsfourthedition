package graphs.undirectedgraphs;

/*
 * 1.深度优先探索：（相当于走迷宫）
 * （1）选择一条没有标记过的通道，铺上一条绳子，不断往下走
 * （2）当来到一个标记过的路口时，用绳子回退到上个路口
 * （3）当回退到的路口都被标记过时，继续回退
 * 
 */

public class DepthFirstSearch {
//	标记访问过的结点
	private boolean[] marked;
//	以s为起点的连通分量中结点的个数
	private int count;
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
//	不断往下延伸访问
	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
//		递归地访问没有被标记过的邻居结点
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}
	
//	结点w是否在该连通分量中
	public boolean marked(int w) {
		return marked[w];
	}
	
	public int count() {
		return count;
	}
}
