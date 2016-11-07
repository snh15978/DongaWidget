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
	//����ڰ� ���信�� �����۾��� �ϰ� �Ϸ��Ͽ����� ȣ��Ǵ� �޼ҵ�
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		//getElementsByTagName()���ο� ���ϴ��±׿� ���±��� ��ġ�� ����Ͽ� �ش��±� ���θ� ������ �´�.
		//���̾�α׸� ����.
		BookLoginActivity.mProgress = ProgressDialog.show(context, "Wait", "Downloading...");
		web.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementsByTagName('html')[0].innerHTML);");
		CookieSyncManager.getInstance().sync();
		return true;
	}
	//������� ���۰��� ������� ������ �������� �ε��� ������ ȣ��Ǵ� �޼ҵ�
	@Override
	public void onPageFinished(WebView view, String url) {
		//web.loadUrl("javascript:window.HtmlViewer.showHTML(document.getElementsByTagName('html')[0].innerHTML);");
	}
}
