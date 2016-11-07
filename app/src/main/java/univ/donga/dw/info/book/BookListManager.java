package univ.donga.dw.info.book;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class BookListManager{
	private int countOfPosibleExtend;
	//대출목록을 저장할 리스트
	private ArrayList<BookInfo> bookList;
	public BookListManager()
	{
		bookList = new ArrayList<BookInfo>();
	}
	public void addBookList(BookInfo bookInfo)
	{
		bookList.add(bookInfo);
	}
	public BookInfo getBookList(int i)
	{
		return bookList.get(i);
	}
	public void removeBookList(int i)
	{
		bookList.remove(i);
	}
	public int getBookListSize()
	{
		return bookList.size();
	}

	public void clearBookList() {
		bookList.clear();
	}
	public int getCountOfPosibleExtend() {
		return countOfPosibleExtend;
	}
	public void setCountOfPosibleExtend(int countOfPosibleExtend) {
		this.countOfPosibleExtend = countOfPosibleExtend;
	}
}
	
