package graphs.directedgraphs;

/*
 * 1.优先级限制下的调度问题
 * 2.拓扑排序：给定一幅有向图，将所有的顶点排序，使得所有的有向边均从排在前面的元素指向排在后面的元素
 * 3.优先级限制下的调度问题等价于计算有向无环图中的所有顶点的拓扑顺序
 * 4.一幅有向无环图的拓扑顺序就是所有顶点的逆后序排列
 * 5.使用深度优先搜索对有向无环图进行拓扑排序所需的时间和V+E成正比
 */

public class Topological {
//	存储拓扑顺序
	private Iterable<Integer> order;
	
//	拓扑排序和有向环的检测是一起的，因为有向环的检测是排序的前提
	public Topological(Digraph G) {
//		判断是否有环
		DirectedCycle  cyclefinder = new DirectedCycle(G);
//		如果没有环
		if(!cyclefinder.hasCycle()) {
//			获得该有向无环图的逆后序，即拓扑顺序
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
//	DAG：有向无环图
	public boolean isDAG() {
		return order != null;
	}
}
