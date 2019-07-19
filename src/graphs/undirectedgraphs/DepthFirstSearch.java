package graphs.undirectedgraphs;

/*
 * 1.�������̽�������൱�����Թ���
 * ��1��ѡ��һ��û�б�ǹ���ͨ��������һ�����ӣ�����������
 * ��2��������һ����ǹ���·��ʱ�������ӻ��˵��ϸ�·��
 * ��3�������˵���·�ڶ�����ǹ�ʱ����������
 * 
 */

public class DepthFirstSearch {
//	��Ƿ��ʹ��Ľ��
	private boolean[] marked;
//	��sΪ������ͨ�����н��ĸ���
	private int count;
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
//	���������������
	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
//		�ݹ�ط���û�б���ǹ����ھӽ��
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}
	
//	���w�Ƿ��ڸ���ͨ������
	public boolean marked(int w) {
		return marked[w];
	}
	
	public int count() {
		return count;
	}
}
