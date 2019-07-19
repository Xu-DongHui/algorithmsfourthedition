package graphs.shortestPaths;

//��Ȩ����ߵĶ���
public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
//	ָ�������ߵĶ���
	public int from() {
		return v;
	}
//	������ָ��Ķ���
	public int to() {
		return w;
	}
}
