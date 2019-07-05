package sorting;
/*
 * 1.自己定义了可以比较的数据类型Date，排序模板适用于任何是实现了Comparable接口的数据类型。
 * 2.排序算法的基础，因为排序算法的目标是将所有元素的主键按照某种方式排列，在java中，对主键的抽象描述是通过Comparable接口实现的
 * 3.compareTo()实现了主键抽象，它给出了实现了Comparable接口的任意数据类型的对象的大小顺序的定义
 */
public class Date implements Comparable<Date> {
	private final int day;
	private final int month;
	private final int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int day() {
		return day;
	}
	
	public int month() {
		return month;
	}
	
	public int year() {
		return year;
	}
	
	//compareTo()定义了目标类型对象的自然次序
	//compartTo()必须实现一个全序关系：自反性，反对称性，传递性
	@Override
	public int compareTo(Date that) {
		//对于v<w,v=w和v>w三种情况，java的习惯是在v.compareTo(w)被调用时，返回一个负整数，零和正整数（一般是，-1,0,和1）
		// TODO Auto-generated method stub
		if(this.year > that.year)
			return +1;
		if(this.year < that.year)
			return -1;
		if(this.month > that.month)
			return +1;
		if(this.month < that.month)
			return -1;
		if(this.day > that.day)
			return +1;
		if(this.day < that.day)
			return -1;
		return 0;
	}
	
	public String toString() {
		return year + "/" + month + "/" + day;
	}
}
