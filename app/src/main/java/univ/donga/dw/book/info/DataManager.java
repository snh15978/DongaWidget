package univ.donga.dw.book.info;

import univ.donga.dw.info.book.BookListManager;
import univ.donga.dw.service.CampusManagerService;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class DataManager {

	private final String BOOK_LIST_URL = "http://dalis.donga.ac.kr/m/mLnReport.csp";
	private Context context;
	//대출도서 관리를 할 클래스
	private BookListManager bookListManager;
	
	//로그인후 html소스를 가지고오기위한 스크립트인터페이스
	private BookListDownJavaScriptInterface scrptInterface;
	private BookListDownWebClient bookListDownWebClient;

	public DataManager(Context context)
	{
		Activity activity = (Activity)context;
		this.context = context;
		bookListManager = new BookListManager();
		//쿠키 저장을위한 준비
		CookieSyncManager.createInstance(context);
		CookieSyncManager.getInstance().startSync();
	}
	public String getBOOK_LIST_URL() {
		return BOOK_LIST_URL;
	}
	public void setScrptInterface(BookListDownJavaScriptInterface scrptInterface) {
		this.scrptInterface = scrptInterface;
	}
	public BookListDownJavaScriptInterface getScrptInterface() {
		return scrptInterface;
	}
	public BookListManager getBookListManager()
	{
		return bookListManager;
	}
	public BookListDownWebClient getBookListDownWebClient() {
		return bookListDownWebClient;
	}
	public void setBookListDownWebClient(BookListDownWebClient bookListDownWebClient) {
		this.bookListDownWebClient = bookListDownWebClient;
	}
	//자동알람 주기 게터세터
	public void setAuto_book_alarm(boolean value)
	{
		CampusManagerService.auto_book_alarm = value;
	}
	public boolean getAuto_book_alarm()
	{
		return CampusManagerService.auto_book_alarm;
	}
	
}
