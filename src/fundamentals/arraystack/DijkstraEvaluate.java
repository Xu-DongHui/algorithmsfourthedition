package fundamentals.arraystack;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Dijstra���Ͻ�˹��������˫ջ�������ʽ��ֵ�㷨:���´���ֻ֧�ֶ�Ԫ����
public class DijkstraEvaluate {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();//opsջ�洢�����
		/*
		 * 1.�ڷ����У����Ͳ���Item���뱻ʵ����Ϊ���ö���
		 * 2.javaͨ���Զ���װ���ƣ��Զ�װ����Զ����䣩��ʹ���ʹ����ܹ�����ԭʼ��������
		 * 3.����ԭʼ�������ͱ����Ϊ�������Ͳ�����Ϊ���Ͳ���
		 */
		Stack<Double> vals = new Stack<Double>();//valsջ�洢��ֵ
		while(!StdIn.isEmpty()) {// ctrl + z ������ֹ����̨��������ѭ����������
			String s = StdIn.readString();//��ȡ����̨�ϵ����룬�ÿո���
			if(s.equals("("));//����������
			else if(s.equals("+"))
				ops.push(s);
			else if(s.equals("-"))
				ops.push(s);
			else if(s.equals("*"))
				ops.push(s);
			else if(s.equals("/"))
				ops.push(s);
			else if(s.equals("sqrt"))
				ops.push(s);
			//����������ʱ������������������������������������ѹ�������ջ������һ�������žʹ���������ǰ��һ�μ������������Խ��м��㣬�����Ϊ�������ջ��
			else if(s.equals(")")) {
				String op = ops.pop();
				double v = vals.pop();
				if(op.equals("+"))
					v = vals.pop() + v;
				else if(op.equals("-"))
					v = vals.pop() - v;
				else if(op.equals("*"))
					v = vals.pop() * v;
				else if(op.equals("/"))
					v = vals.pop() / v;
				else if(op.equals("sqrt"))
					v = Math.sqrt(v);
				vals.push(v);
			}
			else
				vals.push(Double.parseDouble(s));
		}
		StdOut.print(vals.pop());
	}
}
