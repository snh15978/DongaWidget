package univ.donga.dw.book.info;

import java.util.StringTokenizer;

import univ.donga.dw.book.BookActivity;
import univ.donga.dw.info.book.BookInfo;
import android.content.Context;
import android.os.Handler;

public class BookListDownJavaScriptInterface {
	private Context context;
	private DataManager dataManager;
	private String countOfPosibleExtend;
	private String bookCnt;
	private BookInfo bookInfo;
	private Handler mAfterDown;
	public BookListDownJavaScriptInterface(Context context, DataManager dataManager, Handler mAfterDown)
	{
		this.context = context;	
		this.dataManager = dataManager;
		bookCnt = "";
		this.mAfterDown = mAfterDown;
	}

	// 연장가능횟수는?
	public void downCountOfPosibleExtend(String html) {
		this.countOfPosibleExtend = html;
	}
	//대출한 책이 몇권인가?
	public void downBookCnt(String html)
	{
		StringTokenizer stTok = new StringTokenizer(html, " :  /");
		stTok.nextToken();
		//몇권 빌렷는지 저장해둔다.
		this.bookCnt = stTok.nextToken();
		mAfterDown.sendEmptyMessage(0);
	}
	// 대출한 책번호을 가지고온다.
	public void downBookNumber(String html)
	{
		bookInfo = new BookInfo();
		StringTokenizer stTok = new StringTokenizer(html, ">");
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		//책이름 셋팅
		bookInfo.setBookNumber(stTok.nextToken().replace(" <br", ""));
	}
	//대출한 책이름을 가지고온다.
	public void downBookName(String html)
	{
		StringTokenizer stTok = new StringTokenizer(html, ">");
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		//책 등록번호셋팅
		bookInfo.setBookName(stTok.nextToken().replace("</a",	""));
	}
	
	// 대출한 반납날짜을 가지고온다.
	public void downBookReturnDay(String html)
	{

		StringTokenizer stTok = new StringTokenizer(html, ">");
		stTok.nextToken();
		stTok.nextToken();
		stTok.nextToken();
		String buf = stTok.nextToken().replace("</td", "");
		stTok = new StringTokenizer(buf, ".");
		//반납날짜 셋팅
		bookInfo.setReturnYear(Integer.parseInt(stTok.nextToken()));
		bookInfo.setReturnMonth(Integer.parseInt(stTok.nextToken()));
		bookInfo.setReturnDay(Integer.parseInt(stTok.nextToken()));
	}

	// 연장가능 여부을 가지고온다.
	public void downBookPosibleExtend(String html) {
		//연장불가능하면 불가능표시를 한다.
		if(html.indexOf("연장불가") > 0)
			bookInfo.setPosibleExtend(false);
		else
			bookInfo.setPosibleExtend(true);
		//리스트에 추가한다.
		dataManager.getBookListManager().addBookList(bookInfo);
	}
	//파싱이 끝낫음을 알린다.
	public void endOfParsing(String html)
	{
		mAfterDown.sendEmptyMessage(1);
	}
	//연장하기 버튼용
	public void extendButtn(String html)
	{
		BookActivity.mProgress.dismiss();
	}
	
	public String getBookCnt()
	{
		return bookCnt;
	}
	public BookInfo getBookInfo()
	{
		return bookInfo;
	}

	public String getCountOfPosibleExtend() {
		return countOfPosibleExtend;
	}

	public void setCountOfPosibleExtend(String countOfPosibleExtend) {
		this.countOfPosibleExtend = countOfPosibleExtend;
	}
	
}
