package univ.donga.dw.info.book;

public class BookInfo {
	private String bookName;
	private String bookNumber;
	private int returnYear;
	private int returnMonth;
	private int returnDay;
	private boolean posibleExtend;
	public BookInfo()
	{
		
	}
	public BookInfo(String bookName, String bookNumber, int returnYear, int returnMonth, int returnDay)
	{
		this.bookName = bookName;
		this.bookNumber = bookNumber;
		this.returnYear = returnYear;
		this.returnMonth = returnMonth;
		this.returnDay = returnDay;
	}
	public String getBookName() {
		return bookName;
	}
	public String getBookNumber() {
		return bookNumber;
	}
	public int getReturnYear() {
		return returnYear;
	}
	public int getReturnMonth() {
		return returnMonth;
	}
	public int getReturnDay() {
		return returnDay;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}
	public void setReturnYear(int returnYear) {
		this.returnYear = returnYear;
	}
	public void setReturnMonth(int returnMonth) {
		this.returnMonth = returnMonth;
	}
	public void setReturnDay(int returnDay) {
		this.returnDay = returnDay;
	}
	public boolean isPosibleExtend() {
		return posibleExtend;
	}
	public void setPosibleExtend(boolean posibleExtend) {
		this.posibleExtend = posibleExtend;
	}
	
}
