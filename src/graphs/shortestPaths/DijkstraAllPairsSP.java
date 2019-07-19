package graphs.shortestPaths;

/*
 * �õ����ⶥ���֮������·��
 */

public class DijkstraAllPairsSP {
	private DijkstraSP[] all;
	public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
		all = new DijkstraSP[G.V()];
//		��ÿ�����㶼��Ϊ��㣬��ѯ����������·����
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DijkstraSP(G, v);
		}
	}
	
//	��ö���s�����·�������õ�s��t�����·��
	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);
	}
	
//	�õ�s��t����̾���
	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
}
