package graphs.directedgraphs;

/*
 * 1.有向图中的强连通性：
 * 2.顶点强连通：两个顶点相互可达。
 * 3.如果一幅有向图中的任意顶点都是强连通的，则这幅图为强连通的。
 * 4.两个顶点是强连通的，当且仅当他们都在一个普通的有向环中。
 * 5.强连通分量：强连通性将所有顶点分为了一些由相互均为强连通的顶点组成的子集，这些子集称为强连通分量。
 * 
 * 6.Kosaraju算法：
 * （1）使用深度优先探索查找给定的有向图G的反向图Gr，得到所有顶点的逆后序
 * （2）根据逆后序顶点再次用深度优先搜索处理有向图G
 * （3）每次递归调用所标记的顶点都在同一个强连通分量之中
 */

public class KosarajuSCC {
	private boolean[] marked;
//	存储顶点的强连通分量标志符
	private int[] id;
//	强连通分量标识/个数
	private int count;
	
	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
//		获得反向图的逆后序
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
//		根据逆后序进行深度优先探索
		for(int s : order.reversePost()) {
			if(!marked[s]) {
//				每次递归调用都处在一个强连通分量之中
				dfs(G, s);
				count++;
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int count() {
		return count;
	}
}
