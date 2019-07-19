package graphs.directedgraphs;

import edu.princeton.cs.algs4.Stack;

/*
 * 1.���ȼ������µĵ������⣺����һ�����Ⱥ�˳�����Ƶ��������������������°��������������
 * 2.���һ�������ȼ����Ƶ������д��ڻ�����ô�������϶��޽�
 * 3.�����޻�ͼ��һ���������򻷵�����ͼ
 */

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
//	��������е����ж���
	private Stack<Integer> cycle;
//	ϵͳ�ݹ���õ�ջ����ʾ��ǰ���ڱ���������·����
	private boolean[] onStack;
	
	public DirectedCycle(Digraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v])
				dfs(G, v);
		}
	}
	
	private void dfs(Digraph G, int v) {
//		�ڵݹ���õ�ջ��
		onStack[v] = true;
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(this.hasCycle())
				return;
//			�����ݹ����
			else if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
//			w�Ѿ������ڵݹ���õ�ջ�У���˴��ڻ�
			}else if(onStack[w]) {
				cycle = new Stack<Integer>();
//				�ӵ�ǰ����v��ʼ�����ز�ѯ·���ϵĵ㣬ֱ��������w
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
//				������w��v���뻷��
				cycle.push(w);
				cycle.push(v);
			}
		}
//		����v�ĵݹ���ý���������һ��·��
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
//	���ػ��ڶ��㼯��
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
