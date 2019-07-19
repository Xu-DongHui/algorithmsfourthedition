package graphs.undirectedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/*
 * 1.广度优先搜索，用以解决单点最短路径
 * 2.找到从s到v的最短路径：从s开始，在所有由一条边可以到达的顶点中寻找v，如果找不到，就继续在离两条边的所有顶点中查找v，如此进行
 * 3.广度优先搜索和深度优先搜索的不同在于取数据结构中下一个顶点的规则：
 * （1）广度优先搜索取最早加入的点，类似于扇面扫描，用队列保存访问过的最前端的点。先覆盖起点附近的顶点，然后再向前进。
 * （2）深度优先搜索取最晚加入的点，不断深入图中并在栈中保存了所有分叉的顶点（调用栈）。先寻找离起点最远的顶点，碰到死胡同后才访问近处的顶点。
 */

public class BreadthFirstPaths {
//	表示到达该顶点的最短路径是否已知
	private boolean[] marked;
//	表示s到每个和s连通的顶点的最短路径
	private int[] edgeTo;
//	起点
	private final int s;
	
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s) {
//		使用队列保存所有已经被标记过但其邻接表还没有被检查过的顶点
		Queue<Integer> queue = new Queue<Integer>();
//		标记起点
		marked[s] = true;
		queue.enqueue(s);
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
//			检查每个标记过的顶点的相邻顶点
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
//		从终点开始，通过栈来存储，不断得到路径的上一个节点，直到得到起始节点
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
