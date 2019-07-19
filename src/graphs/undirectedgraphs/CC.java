package graphs.undirectedgraphs;

/*
 * 1.�ҳ�һ��ͼ��������ͨ����
 * 2.��union-find�㷨�Ƚ�
 * ��1���������������union-find�죬������Ҫ��ʱ��Ϊ����o(V+E)
 * ��2��union-find�㷨��ʵ���и��죬��Ϊ������Ҫ���첢��ʾһ��ͼ��
 * ��3����������������ʺ�ʵ����ͼ�ĳ����������͡�
 * 
 */
public class CC {
	private boolean[] marked;
//	������Ϊ�������洢��ͨ�����ı�־��
	private int[] id;
//	ͳ����ͨ�����ĸ���
	private int count;
	
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
//		һ��ͼ�����ж����ͨ����
//		Ѱ��һ��������Ϊÿ����ͨ����������������������
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s]) {
				dfs(G, s);
//				ͳ����ͨ����
				count++;
			}
		}
	}
	
//	�����������
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}
	
//	����v��w�Ƿ���ͨ
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
