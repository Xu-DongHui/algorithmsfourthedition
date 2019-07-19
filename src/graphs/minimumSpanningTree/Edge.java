package graphs.minimumSpanningTree;

/*
 * 1.定义带权重的边
 */

public class Edge implements Comparable<Edge> {
	
//	顶点v和w的边
	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
//	返回顶点v
	public int either() {
		return v;
	}
	
//	返回另一个顶点
	public int other(int vertex) {
		if(vertex == v)
			return w;
		else if(vertex == w)
			return v;
		else throw new RuntimeException("Inconsistent edge");
	}

//	边的自然次序
//	根据边的权重进行比较
	public int compareTo(Edge that) {
		if(this.weight() < that.weight())
			return -1;
		else if(this.weight() > that.weight())
			return 1;
		else 
			return 0;
	}
	
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}

}
