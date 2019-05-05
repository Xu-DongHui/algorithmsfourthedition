package fundamentals;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
	//二分查找：
	//首先数组是有序的
	public static int rank(int key, int[] a) {
		int lo = 0;//标记数组的左边
		int hi = a.length - 1;//标记数组的右边
		while(lo <= hi) {//随着查找的进行，不断折半查找的范围，直到lo大于hi
			int mid = lo + (hi - lo) / 2;//判断中间的数值
			if(key < a[mid])//如果key在中间值的左边，那就将右边的标记改为mid - 1
				hi = mid - 1;
			else if(key > a[mid])//如果key在中间值的右边，那就将左边的标记改为mid + 1
				lo = mid + 1;
			else
				return mid;//找到目标值key
		}
		
		return -1;
	}
	
	//args[]读取有误，暂时不做处理,无文件
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
