package graphs.undirectedgraphs;

/*
 * 1.˫ɫ���⣨����һ������ͼ�𣩣��Ƿ��ܹ���������ɫ��ͼ�����н����ɫ��ʹ������һ���ߵ������˵����ɫ������ͬ��
 */

public class TwoColor {
	private boolean[] marked;
//	��Ƕ������ɫ
	private boolean[] color;
	private boolean isTwoColorable = true;
	
	public TwoColor(Graph G) {
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s])
				dfs(G, s);
		}
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
//				����һ���ڵ����ɫ��ǳ��뵱ǰ�ڵ㲻ͬ����ɫ
				color[w] = !color[v];
				dfs(G, w);
			} else if(color[w] == color[v])
				isTwoColorable = false;
		}
	}
	
	public boolean isBipartite() {
		return isTwoColorable;
	}
}
