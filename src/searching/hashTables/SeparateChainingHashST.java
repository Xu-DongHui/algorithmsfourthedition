package searching.hashTables;

import java.util.HashMap;

import edu.princeton.cs.algs4.SequentialSearchST;

/*
 * ɢ�б�
 * 1.ɢ�к����������ҵļ�ת��Ϊ���������
 * ��1�������ɢ�з�����������������1.һ���ԣ��ȼ۵ļ���Ȼ������ȵ�ɢ��ֵ��2.��Ч�ԣ������㣻3.�����ԣ����ȵ�ɢ�����еļ���java�е�String,Integer,Double,File��URL��hashcode()��ʵ�֡�
 * ��2��������������Horner����
 * ��3�����棺��ÿ������ɢ��ֵ��������
 * 2.������ײ��ͻ�Ĺ��̣�����������ɢ��ֵ��ͬ�����
 * ��1��������������СΪM�������е�ÿ��Ԫ��ָ��һ�����������е�ÿ����㶼�洢��ɢ��ֵΪ��Ԫ�ص������ļ�ֵ�ԡ�ÿ������ĳ���ΪN/M
 * ��2�����ŵ�ַɢ�б��ô�СΪM������洢����N����ֵ��(M>N)���������еĿ�λ�����ײ��ͻ�����ŵ�ַɢ�б�����򵥵ķ���������̽�ⷨ��
 */

public class SeparateChainingHashST<Key, Value> {
//	��ֵ������
	private int N;
//	ɢ�б��С
	private int M;
//	���������������
//	��M��Ԫ�طֱ𹹽����ű�������ɢ�е���ļ�
	private SequentialSearchST<Key, Value>[] st;
	
	public SeparateChainingHashST() {
		this(997);
	}
	
	public SeparateChainingHashST(int M) {
//		����M������
		this.M = M;
//		java�����������飬���ʹ������ת��
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST<Key, Value>();
		}
	}
	
//	��Ĭ�ϵ�hashCode()�����ͳ�����������ϣ�����һ��0��M-1������
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
//	�õ�ֵ
	public Value get(Key key) {
		return st[hash(key)].get(key);
	}
	
//	�����ֵ��
	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}

}
