package univ.donga.dw.login.info;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class BookLoginJavaScriptInterface {
	private Context context;
	private String html;
	private Handler mAfterDown;
	public BookLoginJavaScriptInterface(Context context, Handler mAfterDown)
	{
		this.context = context;	
		html = "";
		this.mAfterDown = mAfterDown;
	}
	public void showHTML(String html)
	{
		this.html = html;
		mAfterDown.sendEmptyMessage(0);
	}
	public String getHtml() {
		return html;
	}
}
