package graphs.shortestPaths;

import edu.princeton.cs.algs4.Bag;
import graphs.directedgraphs.Digraph;

/*
 * 1.���·������һ����Ȩ����ͼ�У��Ӷ���s������t�����·�������д�s��t��·���е�Ȩ����С�ߡ�
 * 2.���·��������sΪ����һ�����·������ͼ��һ����ͼ��������s�ʹ�s�ɴ�����ж��㣬����������ĸ��ڵ���s������ÿ��·����������ͼ�е�һ�����·����
 * 3.��С�����������·��������
 * ��1�� ��С�������ܹ���֤��������ͼ������·��֮����С�������ܱ�֤��������֮�������·����
 * ��2�����·���Ǵ�һ�����������Ŀ�ĵص�·����С��
 */

public class EdgeWeightedDigraph {
	private final int V;
	private int E;
//	ά��һ���ɶ���������bag�������飬bag����洢DirectedEdge����
	private Bag<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
//	�����ֻ�������һ��������ڽӱ���
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for(int v = 0; v < V; v++) {
			for(DirectedEdge e : adj[v])
				bag.add(e);
		}
		return bag;
	}
}
