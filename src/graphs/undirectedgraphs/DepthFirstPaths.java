package graphs.undirectedgraphs;

import edu.princeton.cs.algs4.Stack;

/*
 * 1.������������ܹ�����ܶ��ͼ�йص���������ͨ������
 * 2.��ͨ�����⣺�������������Ƿ���ͨ
 */

public class DepthFirstPaths {
//	�����sΪ������ͨ�����еĽڵ�
	private boolean[] marked;
//	�洢ÿ����s��ͨ�Ķ���ص�s��·��
//	��ÿ������Ϊ����������ֵΪ���ö��㣨����������һ������
//	һ�������sΪ��������������������s��ͨ�Ķ���
	private int[] edgeTo;
//	��ʼ����
	private final int s;
	
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
//				v���Ե�w���洢��v��w��·������Ϊÿ������ֻ�ܷ���һ�Σ���ˣ�ֻ��洢һ����
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	private boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
//		���յ㿪ʼ��ͨ��ջ���洢�����ϵõ�·������һ���ڵ㣬ֱ���õ���ʼ�ڵ�
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
