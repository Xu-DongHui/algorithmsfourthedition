package graphs.directedgraphs;

import edu.princeton.cs.algs4.Bag;

/*
 * 1.������ͼ�У����ǵ���ģ�ÿ���������ӵ�����������һ������ԣ����ǵ��ڽ����ǵ���ġ�
 * 2.һ���������һ����һ������ָ����ָ��ڶ������㡣һ������ĳ���Ϊ�ɸö���ָ���ıߵ�������һ����������Ϊָ��ö���ıߵ�������
 * 3.����·�������򻷡�
 */

public class Digraph {
//	������
	private final int V;
//	����
	private int E;
//	�ڽӱ�
	private Bag<Integer>[] adj;
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
//	����ߣ���vָ��w
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
//	����ԭ����ͼ��һ���������������бߵķ���ת��
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adj(v)) 
				R.addEdge(w, v);
		}
		return R;
	}
}
