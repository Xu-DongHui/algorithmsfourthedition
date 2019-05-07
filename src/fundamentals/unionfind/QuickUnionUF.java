package fundamentals.unionfind;
/*
 * 1.quick-union的快速算法的实现
 * 2.链接：每个触点所对应的id[]元素都是同一个分量中的另一个触点的名称。同一个分量中的触点一个连一个，直到连接到根节点。根节点是链接指向自己的触点
 * 3.当且仅当分别由两个触点开始的连接过程到达了同一个根触点时，他们才存在于同一个连通分量中
 * 4.时间复杂度分析：解决动态连通性问题，并且只得到一个连通变量，至少需要调用N-1次union()，每次union()中两次find(),
 * 	 find()最好的情况只需访问数组一次就能得到根节点，最坏的情况和树的最大的深度有关，为o(n),因此最坏情况下的时间复杂度仍旧为o(n^2)。
 * 5.quick-union算法可以看做是quick-find算法的改进，解决了quick-find算法中union()总是线性级别的问题，一般情况下，quick-union算法比quick-find算法快
 */
public class QuickUnionUF {
	private int[] id;//以触点作为数组索引，数组值为该触点所连接的下一个触点：父链接数组
	private int count;//分量数量
	public QuickUnionUF(int N) {//指定了节点数量，可以改为动态大小的quick-union算法
		//一开始有N个触点和N个分量，每个触点都构成了一个只含有他自己的的分量
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;//存储自己的连通分量的根节点，初始时，每个节点都是根节点
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
	
	//获取每个触点p所对应的根节点
	public int find(int p) {
		//找到根节点：当p不是根节点时，获取下一个节点继续判断
		while (p != id[p])
			p = id[p];
		//返回根节点
		return p;
	}
	
	//在p和q之间建立一条连接:将p和q归并到相同的分量中
	public void union(int p, int q) {
		//由p,q分别找到它们的根节点，只需将一个根节点连接到另一个根节点即可将一个分量归并到另一个分量中
		int pRoot = find(p);
		int qRoot = find(q);
		//根节点相同，即属于同一个连通分量
		if(pRoot == qRoot)
			return;
		//将p和q归于同一个分量中
		id[pRoot] = qRoot;
		
		count--;
	}
}
