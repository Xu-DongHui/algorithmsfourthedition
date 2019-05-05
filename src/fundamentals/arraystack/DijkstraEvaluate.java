package fundamentals.arraystack;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//Dijstra（迪杰斯特拉）的双栈算术表达式求值算法:以下代码只支持二元运算
public class DijkstraEvaluate {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();//ops栈存储运算符
		/*
		 * 1.在泛型中，类型参数Item必须被实例化为引用对象
		 * 2.java通过自动包装机制（自动装箱和自动拆箱），使泛型代码能够处理原始数据类型
		 * 3.即，原始数据类型必须变为引用类型才能作为泛型参数
		 */
		Stack<Double> vals = new Stack<Double>();//vals栈存储数值
		while(!StdIn.isEmpty()) {// ctrl + z 可以终止控制台，跳出该循环，输出结果
			String s = StdIn.readString();//读取控制台上的输入，用空格间隔
			if(s.equals("("));//忽略左括号
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
			//遇到右括号时，弹出运算符，弹出所需操作数，将计算结果压入操作数栈。遇到一个右括号就代表右括号前的一段计算完整，可以进行计算，结果作为整体存入栈中
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
