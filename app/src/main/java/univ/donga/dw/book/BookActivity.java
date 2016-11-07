package univ.donga.dw.book;

import univ.donga.dw.R;
import univ.donga.dw.book.event.AutoBookAlarmToggleBtnEvent;
import univ.donga.dw.book.info.BookListDownJavaScriptInterface;
import univ.donga.dw.book.info.BookListDownWebClient;
import univ.donga.dw.book.info.DataManager;
import univ.donga.dw.book.info.ViewManager;
import univ.donga.dw.info.book.BookInfo;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ToggleButton;

public class BookActivity extends Activity {

	private ViewManager viewManager;
	private DataManager dataManager;
	public static ProgressDialog mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		
		viewManager = new ViewManager(this);
		dataManager = new DataManager(this);
		
		viewManager.getAuto_book_alarm_togglebnt().setChecked(dataManager.getAuto_book_alarm());
		viewManager.getAuto_book_alarm_togglebnt().setOnClickListener(new AutoBookAlarmToggleBtnEvent(viewManager, dataManager));

		//���⸮��Ʈ�� ������ �´�.
		openBookLayout();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}
	public void openBookLayout()
	{	
		//���信 ���� ������ �Ѵ�.
		WebSettings set = viewManager.getBook_web().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		
		//����κ��� ��Ű�� �����ϱ����� ���ȴ�.
		dataManager.setScrptInterface(new BookListDownJavaScriptInterface(this, dataManager, bookListDownloadHandler));
		viewManager.getBook_web().addJavascriptInterface(dataManager.getScrptInterface(), "HtmlViewer");
		dataManager.setBookListDownWebClient(new BookListDownWebClient(this, viewManager.getBook_web()));
		viewManager.getBook_web().setWebViewClient(dataManager.getBookListDownWebClient());
		viewManager.getBook_web().loadUrl(dataManager.getBOOK_LIST_URL());
	}
	//���⸮��Ʈ�� �о�� �ڵ鷯
	Handler bookListDownloadHandler = new Handler() {
		public void handleMessage(Message msg) {
			mProgress.dismiss();
			switch(msg.what)
			{
			case 0 :
				//������ å�� �Ǽ��� ������ �ͼ� �������ش�.
				//�������� �ʱ�ȭ�� ��Ų��
				dataManager.getBookListManager().clearBookList();
				//���尡��Ƚ���� ������ ����
				dataManager.getBookListManager().setCountOfPosibleExtend(Integer.parseInt(dataManager.getScrptInterface().getCountOfPosibleExtend()));
				//����Ǽ��� ���������
				dataManager.getBookListDownWebClient().setBookCnt(Integer.parseInt(dataManager.getScrptInterface().getBookCnt()));
				//����Ǽ��� �̿��ؼ� ����� ������ �´�.
				viewManager.getBook_web().loadUrl(dataManager.getBOOK_LIST_URL());
				break;
			case 1 :
				//�Ľ��� �������� ����Ʈ�� �ѷ��ش�.
				viewManager.getBook_list().setAdapter(new BookListAdapter(BookActivity.this, viewManager, dataManager));
				break;
			}
		}
	};
}
