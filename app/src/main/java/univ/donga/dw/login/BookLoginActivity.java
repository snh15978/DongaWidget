package univ.donga.dw.login;

import univ.donga.dw.R;
import univ.donga.dw.book.BookActivity;
import univ.donga.dw.carte.CarteActivity;
import univ.donga.dw.login.info.DataManager;
import univ.donga.dw.login.info.BookLoginWebClient;
import univ.donga.dw.login.info.BookLoginJavaScriptInterface;
import univ.donga.dw.login.info.ViewManager;
import univ.donga.dw.thread.CarteDownThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.widget.Toast;


public class BookLoginActivity extends Activity {
	private DataManager dataManager;
	private ViewManager viewManager;
    public static ProgressDialog mProgress = null;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_login);
		dataManager = new DataManager(this);
		viewManager = new ViewManager(this);
		//로그인을위한 화면을 띄워준다.
		openLoginLayout();
	}
	protected void onPause() {
		super.onPause();
		//추가시켯던 웹뷰를 제거시킨다.
		viewManager.getBook_login_layout().removeAllViews();
	}
	public void openLoginLayout()
	{
		//다이얼로그에 띄울 커스텀뷰를 초기화준다.
		viewManager.initBookLoginDialogView();
		new AlertDialog.Builder(this)
		.setTitle("경고메세지")
		.setView(viewManager.getBookLoginDialogView())
		.setPositiveButton("확인", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//자동로그인 체크유무를 저장한다.
				dataManager.setAuto_login(viewManager.getBook_auto_login_check().isChecked());
			}
		})
		.setNegativeButton("취소", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//취소시 액티비티 종료하고 메뉴로 넘어간다.
				dataManager.setAuto_login(false);
				setResult(RESULT_CANCELED);
				finish();
			}
		})
		.show();

		//웹뷰에 각종 설정을 한다.
		WebSettings set = viewManager.getWeb().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		
		//웹뷰로부터 쿠키를 저장하기위해 사용된다.
		dataManager.setScrptInterface(new BookLoginJavaScriptInterface(this, loginHandler));
		viewManager.getWeb().addJavascriptInterface(dataManager.getScrptInterface(), "HtmlViewer");
		viewManager.getWeb().setWebViewClient(new BookLoginWebClient(this, viewManager.getWeb()));
		viewManager.getWeb().loadUrl(dataManager.getLOGIN_URL());
	}	

	//로그인후 성공여부를 처리할 핸들러
	Handler loginHandler = new Handler() {
		public void handleMessage(Message msg) {
			mProgress.dismiss();
			//로그인에 성공하면 북액티비티를 띄운다.
			if(dataManager.getScrptInterface().getHtml().indexOf("SSO") > 0 && 
					dataManager.getScrptInterface().getHtml().indexOf("http://dalis.donga.ac.kr/dalis/Login/sso/LoginPortalSSO2.csp") > 0)
			{
				Toast.makeText(BookLoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}
			//로그인에실패시 메인화면으로 돌아간다.
			else
			{
				Toast.makeText(BookLoginActivity.this, "로그인 실패 메인화면으로 넘어갑니다.", Toast.LENGTH_SHORT).show();
				dataManager.setAuto_login(false);
				setResult(RESULT_CANCELED);
				finish();
			}
		}
	};
}
