package graphs.shortestPaths;

//加权有向边的定义
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
//	指出这条边的顶点
	public int from() {
		return v;
	}
//	这条边指向的顶点
	public int to() {
		return w;
	}
}
