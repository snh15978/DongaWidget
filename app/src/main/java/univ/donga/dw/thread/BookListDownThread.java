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
		//���信 ���� ������ �Ѵ�.
		WebSettings set = bookListServiceManager.getBookWebView().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
				
		//����κ��� ��Ű�� �����ϱ����� ���ȴ�.
		bookListServiceManager.setScrptInterface(new BookListDownJavaScriptInterface(bookListServiceManager, bookListDownloadHandler));
		bookListServiceManager.getBookWebView().addJavascriptInterface(bookListServiceManager.getScrptInterface(), "HtmlViewer");
		
		bookListServiceManager.setBookListDownWebClient(new BookListDownWebClient(bookListServiceManager.getBookWebView()));
		bookListServiceManager.getBookWebView().setWebViewClient(bookListServiceManager.getBookListDownWebClient());
	}
	public void run()
	{
		bookListServiceManager.getBookWebView().loadUrl(bookListServiceManager.getBOOK_LIST_URL());
	}
	//���⸮��Ʈ�� �о�� �ڵ鷯
	Handler bookListDownloadHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what)
			{
			case 0 :
				//������ å�� �Ǽ��� ������ �ͼ� �������ش�.
				//�������� �ʱ�ȭ�� ��Ų��
				bookListServiceManager.getBookListManager().clearBookList();
				//���尡��Ƚ���� ������ ����
				bookListServiceManager.getBookListManager().setCountOfPosibleExtend(Integer.parseInt(bookListServiceManager.getScrptInterface().getCountOfPosibleExtend()));
				//����Ǽ��� ���������
				bookListServiceManager.getBookListDownWebClient().setBookCnt(Integer.parseInt(bookListServiceManager.getScrptInterface().getBookCnt()));
				//����Ǽ��� �̿��ؼ� ����� ������ �´�.
				bookListServiceManager.getBookWebView().loadUrl(bookListServiceManager.getBOOK_LIST_URL());
				break;
			case 1 :
				String imsi = "";
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONDAY) + 1;
				int day = cal.get(Calendar.DAY_OF_MONTH);
				
				//������ �ݳ������� å�� üũ�Ͽ� ����صд�.
				for(int i = 0; i<bookListServiceManager.getBookListManager().getBookListSize(); i++)
				{
					if(year == bookListServiceManager.getBookListManager().getBookList(i).getReturnYear()
							&& month == bookListServiceManager.getBookListManager().getBookList(i).getReturnMonth()
							&& day == bookListServiceManager.getBookListManager().getBookList(i).getReturnDay())
						imsi = imsi + bookListServiceManager.getBookListManager().getBookList(i).getBookName()+"\n";
				}
				//���ó�¥�� ���Ͽ� ������ ����ش�.
				Notification noti = new Notification(R.drawable.icon, "���⿬���ϼ���", System.currentTimeMillis());
				noti.defaults |= Notification.DEFAULT_SOUND;
				noti.flags |= Notification.FLAG_INSISTENT;
				Intent intent = new Intent(context, BookActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PendingIntent content = PendingIntent.getActivity(context, 0, intent, 0);
				noti.setLatestEventInfo(context, "��ġ�Ͽ� ����ȭ������", imsi, content);
				((Service)context).startForeground(2, noti);
				break;
			}
		}
	};
}
