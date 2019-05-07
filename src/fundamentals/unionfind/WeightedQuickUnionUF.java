package fundamentals.unionfind;
/*
 * 1.weight-quick-union�㷨��ʵ��
 * 2.��¼ÿ�����Ĵ�С�������ǽ���С�������ӵ��ϴ�����ϣ���֤��������������ȱȽ�ƽ���������м��˵���ȳ��֣�����find()��ʱ�临�Ӷ�
 * 3.����N�����㣬��Ȩquick-union�㷨�����ɭ���е�����ڵ��������ΪlgN����֤��find()�������������
 * 4.ʱ�临�Ӷȷ�����M�����Ӻ�N������,�����ҪcMlgN�η�������
 * 5.��Ȩquick-union�㷨�������㷨��Ψһһ�����ڽ������ʵ��������㷨
 */
public class WeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	private int count;//��ͨ����������
	public WeightedQuickUnionUF(int N) {
		count = N;
		id = new int[N];//����������
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
		sz = new int[N];//ÿ�����ڵ�����Ӧ�ķ����Ĵ�С
		for(int i = 0; i < N; i++) {//ԭʼ���ڵ��ʼ������С��Ϊ1
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
		//���������ҵ����ڵ�
		while(p != id[p])
			p = id[p];
		return p;
	}
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot)
			return;
		//��С���ĸ��ڵ����ӵ������ĸ��ڵ㣬�����¸��ڵ�����Ӧ�����Ĵ�С
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
