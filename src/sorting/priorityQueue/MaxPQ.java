package sorting.priorityQueue;

/*
 * 1.优先队列：能够插入元素和删除最大元素
 * 2.堆：
 * （1）堆有序：一棵二叉树的每个结点都大于等于它的两个子结点。
 * （2）完全二叉树可以只用数组就能表示，根节点位置在1，它的子结点在位置2,3，而子节点的子节点分别在位置4,5,6,7
 * （3）二叉堆：是一组能够用堆有序的完全二叉树排序的元素。
 * （4）在一个堆中，位置k的节点的父节点的位置为[k/2],则它的两个子节点位置分别是2k和2k+1
 * 3.堆的有序化
 * （1）由下至上的堆有序化（上浮）：如果堆的有序状态因为某个节点变得比父节点大，则需要通过交换它和它的父节点来修复堆。
 * （2）由上至下的堆有序化（下沉）：...父节点变得比子节点小...
 */

public class MaxPQ <Key extends Comparable<Key>> {
//	存放堆元素的数组，基于堆的完全二叉树
	private Key[] pq;
//	元素个数，存储于a[1,..,N]中，pq[0]没有使用
	private int N = 0;
	
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN + 1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
//	插入元素：将新元素插入到数组末尾，增加堆的大小，并让新元素上浮到合适的位置
	public void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	
//	在数组顶端删去最大的元素，并将最后一个元素放到顶端，减小堆的大小，并让这个元素下沉到合适的位置
	public Key delMax() {
//		根节点为最大元素
		Key max = pq[1];
		exch(1, N--);
//		防止对象游离
		pq[N + 1] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
//	上浮
	private void swim(int k) {
//		如果子节点比父节点大，则交换父节点和子节点，不断往上交换
		while(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
//	下沉
	private void sink(int k) {
//		如果父节点比子节点小，则将父节点与子节点中最大的节点交换，不断向下交换
		while(2 * k <= N) {
//			子节点
			int j = 2 * k;
//			选取两个子节点中更大的值
			if(j < N && less(j, j + 1))
				j++;
			if(!less(k, j))
				break;
//			交换父子节点
			exch(k, j);
//			不断往下
			k = j;
		}
	}

}

/*基于堆结构实现堆排序
 public static void sort(Comparable[] a) {
	int N = a.length;

//	构造有序堆；叶节点的个数是N/2;
	for(int k = N/2; k >= 1; k--) {
		sink(a, k, N);
	}
	
//	数组有序，将根节点调换到数组末尾，再进行堆的有序化
	while(N > 1) {
		exch(a, 1, N--);
		sink(a, 1, N);
	}
}
 */

