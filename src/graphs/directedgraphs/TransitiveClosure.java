package graphs.directedgraphs;

/*
 * ����Ŀɴ��ԣ��Ƿ����һ����һ����������v����һ�����������w��·��
 */

public class TransitiveClosure {
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph G) {
		all = new DirectedDFS[G.V()];
//		��¼ÿ������Ŀɴﶥ��
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DirectedDFS(G, v);
		}
	}
	
//	w�Ǵ�v�ɴ����
	public boolean reachable(int v, int w) {
		return all[v].marked(w);
	}
}
