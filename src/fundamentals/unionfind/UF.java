package fundamentals.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * 1.动态连通性-union-find功能的实现，但具体是怎么实现find和union的，可以分为三种不同的算法。1.quick-find，2.quick-union，3.weight-quick-union
 * 2.判断给定的正数对（每个正数称之为触点）是否连接，连接的正数对属于同一个连通分量
 * 3.连通的过程：一开始有N个触点和N个连接分量，每个触点都构成了一个只含有他自己的的分量，接着可以建立分量之间的连通，直到所有的触点都连通，即只有一个连通变量
 * 4.时间复杂度分析：解决动态连通性问题，并且只得到一个连通变量，至少需要调用N-1次union()，每次union()要遍历数组一次，因此时间复杂度为o(n^2)
 */
public class UF {
	private int[] id;//以触点作为数组索引，数组值为该触点属于的连通分量
	private int count;//分量数量
	public UF(int N) {//指定了节点数量，可以改为动态大小的unionfind算法
		//一开始有N个触点和N个分量，每个触点都构成了一个只含有他自己的的分量
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;//存储自己的连通分量的标识,在同一个连通变量中的所有触点在id[]中的值必须全部相同
		}
	}
	//返回连通分量的个数
	public int count() {
		return count;
	}
	
	//判断p和q是否连接，即属于同一连通分量
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	//获取每个触点p所属分量的标志符
	public int find(int p) {
		return id[p];
	}
	
	//在p和q之间建立一条连接:将p和q归并到相同的分量中
	public void union(int p, int q) {
		//查询p和q的连通分量
		int pID = find(p);
		int qID = find(q);
		//如果p和q属于同一连通分量，就不处理
		if(pID == qID)
			return;
		//遍历这个触点数组，当触点属于pID分量时，将该触点分为qID分量
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pID)
				id[i] = qID;
		}
		count--;//分量归并，总分量数量减少
	}
	
	//算法用例
	public static void main(String[] args) {
		int N = StdIn.readInt();
		UF uf = new UF(N);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(uf.count() + "components");
	}
}
