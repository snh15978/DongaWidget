package univ.donga.dw.job.info;

import univ.donga.dw.book.BookActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebClient extends WebViewClient{
	private Context context;
	public MyWebClient(Context context)
	{
		this.context = context;
	}
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
		handler.proceed();
	}
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return true;
	}
	
}
