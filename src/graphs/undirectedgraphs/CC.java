package graphs.undirectedgraphs;

/*
 * 1.找出一幅图的所有连通分量
 * 2.和union-find算法比较
 * （1）深度优先搜索比union-find快，保存需要的时间为常数o(V+E)
 * （2）union-find算法在实际中更快，因为它不需要构造并表示一张图。
 * （3）深度优先搜索更适合实现了图的抽象数据类型。
 * 
 */
public class CC {
	private boolean[] marked;
//	顶点作为索引，存储连通分量的标志符
	private int[] id;
//	统计连通分量的个数
	private int count;
	
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
//		一个图可能有多个连通分量
//		寻找一个顶点作为每个连通分量中深度优先搜索的起点
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s]) {
				dfs(G, s);
//				统计连通分量
				count++;
			}
		}
	}
	
//	深度优先搜索
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}
	
//	顶点v和w是否连通
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int count() {
		return count;
	}
}
