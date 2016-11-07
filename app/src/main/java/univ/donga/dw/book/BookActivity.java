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

		//대출리스트를 가지고 온다.
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
		//웹뷰에 각종 설정을 한다.
		WebSettings set = viewManager.getBook_web().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		
		//웹뷰로부터 쿠키를 저장하기위해 사용된다.
		dataManager.setScrptInterface(new BookListDownJavaScriptInterface(this, dataManager, bookListDownloadHandler));
		viewManager.getBook_web().addJavascriptInterface(dataManager.getScrptInterface(), "HtmlViewer");
		dataManager.setBookListDownWebClient(new BookListDownWebClient(this, viewManager.getBook_web()));
		viewManager.getBook_web().setWebViewClient(dataManager.getBookListDownWebClient());
		viewManager.getBook_web().loadUrl(dataManager.getBOOK_LIST_URL());
	}
	//대출리스트를 읽어올 핸들러
	Handler bookListDownloadHandler = new Handler() {
		public void handleMessage(Message msg) {
			mProgress.dismiss();
			switch(msg.what)
			{
			case 0 :
				//대출한 책의 권수를 가지고 와서 세팅해준다.
				//대출목록을 초기화를 시킨후
				dataManager.getBookListManager().clearBookList();
				//연장가능횟수를 가지고 오고
				dataManager.getBookListManager().setCountOfPosibleExtend(Integer.parseInt(dataManager.getScrptInterface().getCountOfPosibleExtend()));
				//대출권수를 가지고오고
				dataManager.getBookListDownWebClient().setBookCnt(Integer.parseInt(dataManager.getScrptInterface().getBookCnt()));
				//대출권수를 이용해서 목록을 가지고 온다.
				viewManager.getBook_web().loadUrl(dataManager.getBOOK_LIST_URL());
				break;
			case 1 :
				//파싱이 끝났으면 리스트에 뿌려준다.
				viewManager.getBook_list().setAdapter(new BookListAdapter(BookActivity.this, viewManager, dataManager));
				break;
			}
		}
	};
}
