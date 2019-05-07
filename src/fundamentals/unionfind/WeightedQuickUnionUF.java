package fundamentals.unionfind;
/*
 * 1.weight-quick-union算法的实现
 * 2.记录每棵树的大小，并总是将较小的数连接到较大的树上，保证最后整体的树的深度比较平均，不会有极端的深度出现，降低find()的时间复杂度
 * 3.对于N个触点，加权quick-union算法构造的森林中的任意节点的深度最多为lgN，保证了find()对数级别的性能
 * 4.时间复杂度分析：M条链接和N个触点,最多需要cMlgN次访问数组
 * 5.加权quick-union算法是三种算法中唯一一种用于解决大型实际问题的算法
 */
public class WeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	private int count;//连通分量的数量
	public WeightedQuickUnionUF(int N) {
		count = N;
		id = new int[N];//父链接数组
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
		sz = new int[N];//每个根节点所对应的分量的大小
		for(int i = 0; i < N; i++) {//原始根节点初始分量大小都为1
			sz[i] = 1;
		}
	}
	
	public int count() {
		return count;
	}
	public boolean connected(int p, int q) {
		return find(q) == find(q);
	}
	public int find(int p) {
		//跟随链接找到根节点
		while(p != id[p])
			p = id[p];
		return p;
	}
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot)
			return;
		//将小树的根节点连接到大树的根节点，并更新根节点所对应分量的大小
		if(sz[pRoot] < sz[qRoot]) {
			id[pRoot] = id[qRoot];
			sz[qRoot] = sz[qRoot] + sz[qRoot];
		}else {
			id[qRoot] = id[pRoot];
			sz[pRoot] += sz[qRoot];
		}
		count--;
	}
}
