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
	//���⵵�� ������ �� Ŭ����
	private BookListManager bookListManager;
	
	//�α����� html�ҽ��� ������������� ��ũ��Ʈ�������̽�
	private BookListDownJavaScriptInterface scrptInterface;
	private BookListDownWebClient bookListDownWebClient;

	public DataManager(Context context)
	{
		Activity activity = (Activity)context;
		this.context = context;
		bookListManager = new BookListManager();
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
	//�ڵ��˶� �ֱ� ���ͼ���
	public void setAuto_book_alarm(boolean value)
	{
		CampusManagerService.auto_book_alarm = value;
	}
	public boolean getAuto_book_alarm()
	{
		return CampusManagerService.auto_book_alarm;
	}
	
}
