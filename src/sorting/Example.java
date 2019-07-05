package sorting;

import edu.princeton.cs.algs4.StdOut;
/*
 * 1.�����㷨��ģ�壬�����˳��������㷨�����в�����ϰ��Լ��
 * 2.�����㷨���Է�Ϊ���ࣺ
 * 	��1�����˺������������ջ�͹̶���Ŀ��ʵ������֮�⣬��������ڴ�ĵ�ԭ�������㷨
 * 	��2����Ҫ�����ڴ�ռ����洢��һ�����鸱�������������㷨
 */
public class Example {
	//�������
	public static void sort(Comparable[] a) {
		
	}
	//�Ƚ�����Ԫ�صĴ�С��v<wΪ-1
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	//����λ��i,j��Ԫ��
	public static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	//���д�ӡ����
	public static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	//�ж������Ƿ�����
	public static boolean isSorted(Comparable[] a) { 
		for(int i = 1; i < a.length; i++) {
			//�����Ԫ����ǰ���Ԫ�����
			if(less(a[i], a[i-1]))
				return false;
		}
		return true;
	}
}
