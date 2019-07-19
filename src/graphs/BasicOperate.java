package graphs;

import edu.princeton.cs.algs4.Graph;

public class BasicOperate {
//	����v�Ķ���
	public static int degree(Graph G, int v) {
		int degree = 0;
		for(int w : G.adj(v))
			degree++;
		return degree;
	}
	
//	�������ж����������
	public static int MaxDegree(Graph G) {
		int max = 0;
		for(int v = 0; v < G.V(); v++) {
			if(degree(G, v) > max)
				max = degree(G, v);
		}
		return max;
	}
	
//	�������ж����ƽ������
	public static double avgDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}
	
//	�����Ի�������
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for(int v = 0; v < G.V(); v++) {
			for(int w : G.adj(v)) {
				if(w == v)
					count++;
			}
		}
//		��ͼ��adj()����������ÿ���߶����ǹ�����
		return count/2;
	}
}
