package graphs.directedgraphs;

/*
 * 1.����ɴ��ԣ�����һ��ͼ��һ�����s���Ƿ����һ����s�����������v������·��
 * 2.���ɴ��ԣ�����һ��ͼ�Ͷ���ļ��ϣ��Ƿ����һ���Ӽ����еĶ��㵽���������v������·��
 * 3.���������ڴ�����Ӧ���ڱ��-������������ղ��ԡ�
 * 4.���ڲ�������ͼ��·����˵��ֻ��Ҫ������ͼ��DepthFirstPaths��BreadthFirstPaths�е�Graph������ΪDigraph�������ɣ�������������ֲ���
 */

public class DirectedDFS {
	private boolean[] marked;
	
//	����ɴ���
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
//	���ɴ���
	public DirectedDFS(Digraph G, Iterable<Integer> source) {
		marked = new boolean[G.V()];
		for(int s : source) {
//			����s�Ѿ��������������ǹ���
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
	
//	����v�Ƿ�ɴ�
	public boolean marked(int v) {
		return marked[v];
	}
}
