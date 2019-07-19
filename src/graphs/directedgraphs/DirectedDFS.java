package graphs.directedgraphs;

/*
 * 1.单点可达性：给定一幅图和一个起点s，是否存在一条从s到达给定顶点v的有向路径
 * 2.多点可达性：给定一幅图和顶点的集合，是否存在一条从集合中的顶点到达给定顶点v的有向路径
 * 3.可以用于内存清理，应用于标记-清理的垃圾回收策略。
 * 4.对于查找有向图的路径来说，只需要将无向图的DepthFirstPaths和BreadthFirstPaths中的Graph参数换为Digraph参数即可，代码的其他部分不变
 */

public class DirectedDFS {
	private boolean[] marked;
	
//	单点可达性
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
//	多点可达性
	public DirectedDFS(Digraph G, Iterable<Integer> source) {
		marked = new boolean[G.V()];
		for(int s : source) {
//			可能s已经被从其他顶点标记过了
			if(!marked[s])
				dfs(G, s);
		}
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}
	
//	顶点v是否可达
	public boolean marked(int v) {
		return marked[v];
	}
}
