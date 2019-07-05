package sorting;
/*
 * 1.�Լ������˿��ԱȽϵ���������Date������ģ���������κ���ʵ����Comparable�ӿڵ��������͡�
 * 2.�����㷨�Ļ�������Ϊ�����㷨��Ŀ���ǽ�����Ԫ�ص���������ĳ�ַ�ʽ���У���java�У��������ĳ���������ͨ��Comparable�ӿ�ʵ�ֵ�
 * 3.compareTo()ʵ��������������������ʵ����Comparable�ӿڵ������������͵Ķ���Ĵ�С˳��Ķ���
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
	
	//compareTo()������Ŀ�����Ͷ������Ȼ����
	//compartTo()����ʵ��һ��ȫ���ϵ���Է��ԣ����Գ��ԣ�������
	@Override
	public int compareTo(Date that) {
		//����v<w,v=w��v>w���������java��ϰ������v.compareTo(w)������ʱ������һ���������������������һ���ǣ�-1,0,��1��
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
