package fundamentals.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * 1.��̬��ͨ��-union-find���ܵ�ʵ�֣�����������ôʵ��find��union�ģ����Է�Ϊ���ֲ�ͬ���㷨��1.quick-find��2.quick-union��3.weight-quick-union
 * 2.�жϸ����������ԣ�ÿ��������֮Ϊ���㣩�Ƿ����ӣ����ӵ�����������ͬһ����ͨ����
 * 3.��ͨ�Ĺ��̣�һ��ʼ��N�������N�����ӷ�����ÿ�����㶼������һ��ֻ�������Լ��ĵķ��������ſ��Խ�������֮�����ͨ��ֱ�����еĴ��㶼��ͨ����ֻ��һ����ͨ����
 * 4.ʱ�临�Ӷȷ����������̬��ͨ�����⣬����ֻ�õ�һ����ͨ������������Ҫ����N-1��union()��ÿ��union()Ҫ��������һ�Σ����ʱ�临�Ӷ�Ϊo(n^2)
 */
public class UF {
	private int[] id;//�Դ�����Ϊ��������������ֵΪ�ô������ڵ���ͨ����
	private int count;//��������
	public UF(int N) {//ָ���˽ڵ����������Ը�Ϊ��̬��С��unionfind�㷨
		//һ��ʼ��N�������N��������ÿ�����㶼������һ��ֻ�������Լ��ĵķ���
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;//�洢�Լ�����ͨ�����ı�ʶ,��ͬһ����ͨ�����е����д�����id[]�е�ֵ����ȫ����ͬ
		}
	}
	//������ͨ�����ĸ���
	public int count() {
		return count;
	}
	
	//�ж�p��q�Ƿ����ӣ�������ͬһ��ͨ����
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	//��ȡÿ������p���������ı�־��
	public int find(int p) {
		return id[p];
	}
	
	//��p��q֮�佨��һ������:��p��q�鲢����ͬ�ķ�����
	public void union(int p, int q) {
		//��ѯp��q����ͨ����
		int pID = find(p);
		int qID = find(q);
		//���p��q����ͬһ��ͨ�������Ͳ�����
		if(pID == qID)
			return;
		//��������������飬����������pID����ʱ�����ô����ΪqID����
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pID)
				id[i] = qID;
		}
		count--;//�����鲢���ܷ�����������
	}
	
	//�㷨����
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
