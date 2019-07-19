package graphs.directedgraphs;

/*
 * 1.���ȼ������µĵ�������
 * 2.�������򣺸���һ������ͼ�������еĶ�������ʹ�����е�����߾�������ǰ���Ԫ��ָ�����ں����Ԫ��
 * 3.���ȼ������µĵ�������ȼ��ڼ��������޻�ͼ�е����ж��������˳��
 * 4.һ�������޻�ͼ������˳��������ж�������������
 * 5.ʹ��������������������޻�ͼ�����������������ʱ���V+E������
 */

public class Topological {
//	�洢����˳��
	private Iterable<Integer> order;
	
//	������������򻷵ļ����һ��ģ���Ϊ���򻷵ļ���������ǰ��
	public Topological(Digraph G) {
//		�ж��Ƿ��л�
		DirectedCycle  cyclefinder = new DirectedCycle(G);
//		���û�л�
		if(!cyclefinder.hasCycle()) {
//			��ø������޻�ͼ������򣬼�����˳��
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
//	DAG�������޻�ͼ
	public boolean isDAG() {
		return order != null;
	}
}
