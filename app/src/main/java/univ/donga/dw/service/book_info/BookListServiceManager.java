package univ.donga.dw.service.book_info;

import univ.donga.dw.info.book.BookListManager;
import android.app.Activity;
import android.content.Context;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

public class BookListServiceManager {

	private final String BOOK_LIST_URL = "http://dalis.donga.ac.kr/m/mLnReport.csp";
	private Context context;
	//로그인후 html소스를 가지고오기위한 스크립트인터페이스
	private BookListDownJavaScriptInterface scrptInterface;
	private BookListDownWebClient bookListDownWebClient;
	
	//책정보를 저장할 클래스
	private BookListManager bookListManager;
	private WebView bookWebView;
	
	public BookListServiceManager(Context context)
	{
		this.context = context;

		bookListManager = new BookListManager();
		bookWebView = new WebView(context);
		
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
	public WebView getBookWebView()
	{
		return bookWebView;
	}
	
}
