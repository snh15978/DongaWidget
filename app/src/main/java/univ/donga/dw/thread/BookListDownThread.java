package univ.donga.dw.thread;

import java.util.Calendar;

import univ.donga.dw.R;
import univ.donga.dw.book.BookActivity;
import univ.donga.dw.service.book_info.BookListDownJavaScriptInterface;
import univ.donga.dw.service.book_info.BookListDownWebClient;
import univ.donga.dw.service.book_info.BookListServiceManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;

public class BookListDownThread extends Thread{
	
	private Context context;
	private BookListServiceManager bookListServiceManager;

	public BookListDownThread(Context context, BookListServiceManager bookListServiceManager)
	{
		this.context = context;
		this.bookListServiceManager = bookListServiceManager;
		//웹뷰에 각종 설정을 한다.
		WebSettings set = bookListServiceManager.getBookWebView().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
				
		//웹뷰로부터 쿠키를 저장하기위해 사용된다.
		bookListServiceManager.setScrptInterface(new BookListDownJavaScriptInterface(bookListServiceManager, bookListDownloadHandler));
		bookListServiceManager.getBookWebView().addJavascriptInterface(bookListServiceManager.getScrptInterface(), "HtmlViewer");
		
		bookListServiceManager.setBookListDownWebClient(new BookListDownWebClient(bookListServiceManager.getBookWebView()));
		bookListServiceManager.getBookWebView().setWebViewClient(bookListServiceManager.getBookListDownWebClient());
	}
	public void run()
	{
		bookListServiceManager.getBookWebView().loadUrl(bookListServiceManager.getBOOK_LIST_URL());
	}
	//대출리스트를 읽어올 핸들러
	Handler bookListDownloadHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what)
			{
			case 0 :
				//대출한 책의 권수를 가지고 와서 세팅해준다.
				//대출목록을 초기화를 시킨후
				bookListServiceManager.getBookListManager().clearBookList();
				//연장가능횟수를 가지고 오고
				bookListServiceManager.getBookListManager().setCountOfPosibleExtend(Integer.parseInt(bookListServiceManager.getScrptInterface().getCountOfPosibleExtend()));
				//대출권수를 가지고오고
				bookListServiceManager.getBookListDownWebClient().setBookCnt(Integer.parseInt(bookListServiceManager.getScrptInterface().getBookCnt()));
				//대출권수를 이용해서 목록을 가지고 온다.
				bookListServiceManager.getBookWebView().loadUrl(bookListServiceManager.getBOOK_LIST_URL());
				break;
			case 1 :
				String imsi = "";
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONDAY) + 1;
				int day = cal.get(Calendar.DAY_OF_MONTH);
				
				//오늘이 반납날자인 책을 체크하여 기록해둔다.
				for(int i = 0; i<bookListServiceManager.getBookListManager().getBookListSize(); i++)
				{
					if(year == bookListServiceManager.getBookListManager().getBookList(i).getReturnYear()
							&& month == bookListServiceManager.getBookListManager().getBookList(i).getReturnMonth()
							&& day == bookListServiceManager.getBookListManager().getBookList(i).getReturnDay())
						imsi = imsi + bookListServiceManager.getBookListManager().getBookList(i).getBookName()+"\n";
				}
				//오늘날짜와 비교하여 통지를 띄워준다.
				Notification noti = new Notification(R.drawable.icon, "대출연장하세요", System.currentTimeMillis());
				noti.defaults |= Notification.DEFAULT_SOUND;
				noti.flags |= Notification.FLAG_INSISTENT;
				Intent intent = new Intent(context, BookActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PendingIntent content = PendingIntent.getActivity(context, 0, intent, 0);
				noti.setLatestEventInfo(context, "터치하여 연장화면으로", imsi, content);
				((Service)context).startForeground(2, noti);
				break;
			}
		}
	};
}
