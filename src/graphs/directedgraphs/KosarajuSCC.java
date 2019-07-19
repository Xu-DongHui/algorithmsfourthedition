package graphs.directedgraphs;

/*
 * 1.����ͼ�е�ǿ��ͨ�ԣ�
 * 2.����ǿ��ͨ�����������໥�ɴ
 * 3.���һ������ͼ�е����ⶥ�㶼��ǿ��ͨ�ģ������ͼΪǿ��ͨ�ġ�
 * 4.����������ǿ��ͨ�ģ����ҽ������Ƕ���һ����ͨ�������С�
 * 5.ǿ��ͨ������ǿ��ͨ�Խ����ж����Ϊ��һЩ���໥��Ϊǿ��ͨ�Ķ�����ɵ��Ӽ�����Щ�Ӽ���Ϊǿ��ͨ������
 * 
 * 6.Kosaraju�㷨��
 * ��1��ʹ���������̽�����Ҹ���������ͼG�ķ���ͼGr���õ����ж���������
 * ��2����������򶥵��ٴ����������������������ͼG
 * ��3��ÿ�εݹ��������ǵĶ��㶼��ͬһ��ǿ��ͨ����֮��
 */

public class KosarajuSCC {
	private boolean[] marked;
//	�洢�����ǿ��ͨ������־��
	private int[] id;
//	ǿ��ͨ������ʶ/����
	private int count;
	
	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
//		��÷���ͼ�������
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
//		�������������������̽��
		for(int s : order.reversePost()) {
			if(!marked[s]) {
//				ÿ�εݹ���ö�����һ��ǿ��ͨ����֮��
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
