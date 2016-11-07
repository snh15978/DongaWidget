package univ.donga.dw.login.info;

import univ.donga.dw.login.BookLoginActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.http.SslError;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BookLoginWebClient extends WebViewClient{
	private WebView web;
	private Context context;
	public BookLoginWebClient(Context context, WebView web)
	{
		this.context = context;
		this.web = web;
	}
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
		handler.proceed();
	}
	//사용자가 웹뷰에서 무슨작업을 하고 완료하였을때 호출되는 메소드
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		//getElementsByTagName()내부에 원하는태그와 그태그의 위치를 기록하여 해당태그 내부를 가지고 온다.
		//다이얼로그를 띄운다.
		BookLoginActivity.mProgress = ProgressDialog.show(context, "Wait", "Downloading...");
		web.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementsByTagName('html')[0].innerHTML);");
		CookieSyncManager.getInstance().sync();
		return true;
	}
	//사용자의 동작과는 상관없이 웹뷰의 페이지가 로딩이 끝나면 호출되는 메소드
	@Override
	public void onPageFinished(WebView view, String url) {
		//web.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementsByTagName('html')[0].innerHTML);");
	}
}
