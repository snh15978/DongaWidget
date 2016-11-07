package univ.donga.dw.service.book_info;

import univ.donga.dw.info.book.BookListManager;
import android.app.Activity;
import android.content.Context;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

public class BookListServiceManager {

	private final String BOOK_LIST_URL = "http://dalis.donga.ac.kr/m/mLnReport.csp";
	private Context context;
	//�α����� html�ҽ��� ������������� ��ũ��Ʈ�������̽�
	private BookListDownJavaScriptInterface scrptInterface;
	private BookListDownWebClient bookListDownWebClient;
	
	//å������ ������ Ŭ����
	private BookListManager bookListManager;
	private WebView bookWebView;
	
	public BookListServiceManager(Context context)
	{
		this.context = context;

		bookListManager = new BookListManager();
		bookWebView = new WebView(context);
		
		//��Ű ���������� �غ�
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
