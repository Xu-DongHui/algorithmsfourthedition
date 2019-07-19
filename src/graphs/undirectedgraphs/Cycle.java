package graphs.undirectedgraphs;

/*
 * 1.��������������ؼ�⻷��������ͼʾ�޻�ͼ��
 */

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G) {
		marked = new boolean[G.V()];
//		��ͼ�ж����ͨ��ͼ
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s])
				dfs(G, s, s);
		}
	}
	
//	�������������v�ǵ�ǰ�ڵ㣬u��·������һ���ڵ�
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w, v);
//			uҲ��v���ڽӶ��㣬Ҫ���ų�
			} else if(w != u)
//				������������·���⣬����w�Ѿ�������һ��·������ǹ��ˣ�˵��һ���л�
				hasCycle = true;
		}
	}
	
	public boolean hasCycle() {
		return hasCycle;
	}
}
