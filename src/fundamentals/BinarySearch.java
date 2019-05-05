package fundamentals;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
	//���ֲ��ң�
	//���������������
	public static int rank(int key, int[] a) {
		int lo = 0;//�����������
		int hi = a.length - 1;//���������ұ�
		while(lo <= hi) {//���Ų��ҵĽ��У������۰���ҵķ�Χ��ֱ��lo����hi
			int mid = lo + (hi - lo) / 2;//�ж��м����ֵ
			if(key < a[mid])//���key���м�ֵ����ߣ��Ǿͽ��ұߵı�Ǹ�Ϊmid - 1
				hi = mid - 1;
			else if(key > a[mid])//���key���м�ֵ���ұߣ��Ǿͽ���ߵı�Ǹ�Ϊmid + 1
				lo = mid + 1;
			else
				return mid;//�ҵ�Ŀ��ֵkey
		}
		
		return -1;
	}
	
	//args[]��ȡ������ʱ��������,���ļ�
	public static void main(String[] args) {
//		int[] whitelist  = In.readInts(args[0]);
//		Arrays.sort(whitelist);
//		while(!StdIn.isEmpty()) {
//			int key = StdIn.readInt();
//			if(rank(key, whitelist) == -1)
				StdOut.println("key");
//		}
	}
}
