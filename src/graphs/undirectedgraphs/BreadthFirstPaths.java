package graphs.undirectedgraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/*
 * 1.����������������Խ���������·��
 * 2.�ҵ���s��v�����·������s��ʼ����������һ���߿��Ե���Ķ�����Ѱ��v������Ҳ������ͼ������������ߵ����ж����в���v����˽���
 * 3.�������������������������Ĳ�ͬ����ȡ���ݽṹ����һ������Ĺ���
 * ��1�������������ȡ�������ĵ㣬����������ɨ�裬�ö��б�����ʹ�����ǰ�˵ĵ㡣�ȸ�����㸽���Ķ��㣬Ȼ������ǰ����
 * ��2�������������ȡ�������ĵ㣬��������ͼ�в���ջ�б��������зֲ�Ķ��㣨����ջ������Ѱ���������Զ�Ķ��㣬��������ͬ��ŷ��ʽ����Ķ��㡣
 */

public class BreadthFirstPaths {
//	��ʾ����ö�������·���Ƿ���֪
	private boolean[] marked;
//	��ʾs��ÿ����s��ͨ�Ķ�������·��
	private int[] edgeTo;
//	���
	private final int s;
	
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s) {
//		ʹ�ö��б��������Ѿ�����ǹ������ڽӱ�û�б������Ķ���
		Queue<Integer> queue = new Queue<Integer>();
//		������
		marked[s] = true;
		queue.enqueue(s);
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
//			���ÿ����ǹ��Ķ�������ڶ���
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
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
