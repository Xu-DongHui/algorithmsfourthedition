package searching.hashTables;

import java.util.HashMap;

import edu.princeton.cs.algs4.SequentialSearchST;

/*
 * 散列表
 * 1.散列函数将被查找的键转换为数组的索引
 * （1）优秀的散列方法满足三个条件：1.一致性：等价的键必然产生相等的散列值；2.高效性：计算简便；3.均匀性：均匀地散列所有的键。java中的String,Integer,Double,File和URL的hashcode()能实现。
 * （2）除留余数法，Horner方法
 * （3）软缓存：将每个键的散列值缓存起来
 * 2.处理碰撞冲突的过程：处理多个键的散列值相同的情况
 * （1）拉链法：将大小为M的数组中的每个元素指向一个链表，链表中的每个结点都存储了散列值为该元素的索引的键值对。每个链表的长度为N/M
 * （2）开放地址散列表：用大小为M的数组存储保存N个键值对(M>N)，用数组中的空位解决碰撞冲突。开放地址散列表中最简单的方法是线性探测法。
 */

public class SeparateChainingHashST<Key, Value> {
//	键值对总数
	private int N;
//	散列表大小
	private int M;
//	存放链表对象的数组
//	用M个元素分别构建符号表，来保存散列到这的键
	private SequentialSearchST<Key, Value>[] st;
	
	public SeparateChainingHashST() {
		this(997);
	}
	
	public SeparateChainingHashST(int M) {
//		创建M条链表
		this.M = M;
//		java不允许泛型数组，因此使用类型转换
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST<Key, Value>();
		}
	}
	
//	将默认的hashCode()方法和除留余数法结合，产生一个0到M-1的整数
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
//	得到值
	public Value get(Key key) {
		return st[hash(key)].get(key);
	}
	
//	插入键值对
	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}

}
