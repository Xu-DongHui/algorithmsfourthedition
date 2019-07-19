package graphs.minimumSpanningTree;

import edu.princeton.cs.algs4.Bag;

/*
 * 1.��Ȩͼ��ÿ���߹���һ��Ȩ�ص�ͼģ��
 * 2.ͼ����������ͼ��һ�ú������ж�����޻���ͨ��ͼ
 * 3.��С��������һ���������бߵ�Ȩֵ֮����С��������
 * ��1��ֻ������ͨͼ
 * ��2���ߵ�Ȩ�ؿ�����0�����Ǹ���
 * ��3�����бߵ�Ȩ�ظ�����ͬ
 * 4.�����������ʣ�
 * ��1����һ�����������ϵ������������㶼�����һ���µĻ�
 * ��2��������ɾȥһ���߽���õ����ö�������
 * 5.�зֶ���
 * ��1��ͼ���з֣���ͼ�����ж����Ϊ�����ǿ��Ҳ��ص����������ϡ�
 * ��2�����бߣ�һ�������������ڲ�ͬ���ϵĶ���ıߡ�
 * ��3���зֶ�����һ����Ȩͼ�У�����������з֣����ĺ��б��е�Ȩ����С�߱�Ȼ����ͼ����С����������Ȩ�ظ�����ͬ��ǰ���£�
 * ��4��̰���㷨��ʹ���зֶ����ҵ���С��������һ���ߣ������ظ�ֱ���ҵ���С�����������бߡ�
 */

public class EdgeWeightedGraph {
	private final int V;
	private int E;
//	ÿ��������ڽӱ���Ŷ���ı�
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = E;
		adj = (Bag<Edge>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(Edge e) {
//		��ñߵ���������
		int v = e.either();
		int w = e.other(v);
//		v��w��ӱߣ����������
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	
//	���ظ�ͼ�����б�
	public Iterable<Edge> edges() {
		Bag<Edge> b = new Bag<Edge>();
//		����ÿ��������ڽӱ�
		for(int v = 0; v < V; v++) {
			for(Edge e : adj[v]) {
//				w > v ˵���ñ߻�û����ӹ�
				if(e.other(v) > v)
					b.add(e);
			}
		}
		return b;
	}
}
