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
		//�α��������� ȭ���� ����ش�.
		openLoginLayout();
	}
	protected void onPause() {
		super.onPause();
		//�߰����ִ� ���並 ���Ž�Ų��.
		viewManager.getBook_login_layout().removeAllViews();
	}
	public void openLoginLayout()
	{
		//���̾�α׿� ��� Ŀ���Һ並 �ʱ�ȭ�ش�.
		viewManager.initBookLoginDialogView();
		new AlertDialog.Builder(this)
		.setTitle("���޼���")
		.setView(viewManager.getBookLoginDialogView())
		.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//�ڵ��α��� üũ������ �����Ѵ�.
				dataManager.setAuto_login(viewManager.getBook_auto_login_check().isChecked());
			}
		})
		.setNegativeButton("���", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//��ҽ� ��Ƽ��Ƽ �����ϰ� �޴��� �Ѿ��.
				dataManager.setAuto_login(false);
				setResult(RESULT_CANCELED);
				finish();
			}
		})
		.show();

		//���信 ���� ������ �Ѵ�.
		WebSettings set = viewManager.getWeb().getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setLoadWithOverviewMode(true);
		set.setUseWideViewPort(true);
		
		//����κ��� ��Ű�� �����ϱ����� ���ȴ�.
		dataManager.setScrptInterface(new BookLoginJavaScriptInterface(this, loginHandler));
		viewManager.getWeb().addJavascriptInterface(dataManager.getScrptInterface(), "HtmlViewer");
		viewManager.getWeb().setWebViewClient(new BookLoginWebClient(this, viewManager.getWeb()));
		viewManager.getWeb().loadUrl(dataManager.getLOGIN_URL());
	}	

	//�α����� �������θ� ó���� �ڵ鷯
	Handler loginHandler = new Handler() {
		public void handleMessage(Message msg) {
			mProgress.dismiss();
			//�α��ο� �����ϸ� �Ͼ�Ƽ��Ƽ�� ����.
			if(dataManager.getScrptInterface().getHtml().indexOf("SSO") > 0 && 
					dataManager.getScrptInterface().getHtml().indexOf("http://dalis.donga.ac.kr/dalis/Login/sso/LoginPortalSSO2.csp") > 0)
			{
				Toast.makeText(BookLoginActivity.this, "�α��� ����", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}
			//�α��ο����н� ����ȭ������ ���ư���.
			else
			{
				Toast.makeText(BookLoginActivity.this, "�α��� ���� ����ȭ������ �Ѿ�ϴ�.", Toast.LENGTH_SHORT).show();
				dataManager.setAuto_login(false);
				setResult(RESULT_CANCELED);
				finish();
			}
		}
	};
}
