package graphs.undirectedgraphs;

import edu.princeton.cs.algs4.Bag;

/*
 * 1.ͼ����һ�鶥���һ���ܽ��������������ı���ɵ�
 * 2.�Ի���һ������һ�����������ıߡ�
 * 3.·�����ɱ�˳�����ӵ�һϵ�ж��㣻��·����һ��û���ظ������·����
 * 4.����һ�����ٺ���һ�����������յ���ͬ��·�����򵥻���һ��û���ظ�����ͱߵĻ���
 * 5.��ͨͼ������һ�����㶼����һ��·��������һ�����ⶥ��
 * 6.����һ���޻���ͨͼ��ɭ�֣���������������ɵļ��ϡ�
 * 7.ͼ���ܶȣ��Ѿ����ӵĶ����ռ���п��ܵĶ���Եı�����ϡ��ͼ�������ӵĶ���Ժ��٣�����ͼ��ֻ���ٲ��ֶ����û�б����ӡ�
 * 8.����ͼ���ܽ����н���Ϊ�����֣�����ͼ��ÿ���������ӵ��������㶼�ֱ����ڲ�ͬ�Ĳ��֡�
 * 9.ĳ������������������������ıߵ�������
 * 
 * 10.�ڽӱ���ÿ��������������ڽ�㶼�����ڸö����Ӧ��Ԫ����ָ���һ�������С�
 */

public class Graph {
//	������Ŀ
	private final int V;
//	�ߵ���Ŀ
	private int E;
//	�ڽӱ�
	private Bag<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
//		�ڽӱ��ʼ��Ϊ��
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
	
//	���һ������v��w�ı�
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
//	����v�������ڽӶ���
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
}
