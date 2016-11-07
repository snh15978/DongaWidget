package univ.donga.dw.login.info;

import univ.donga.dw.R;
import univ.donga.dw.service.CampusManagerService;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class ViewManager {
	private Context context;
	
	//로그인을 뿌려준 레이아웃정리
	private LinearLayout book_login_layout;
	private WebView web;
	
	//로그인 다이얼로그창을 띄울때 쓰는 커스텀 레이아웃
	private LinearLayout bookLoginDialogView;
	private CheckBox book_auto_login_check;
	
	public ViewManager(Context context)
	{
		this.context = context;
		book_login_layout = (LinearLayout)((Activity)context).findViewById(R.id.book_login_layout);
		web = new WebView(context);
		book_login_layout.addView(web);
	}
	public WebView getWeb() {
		return web;
	}
	public void initBookLoginDialogView()
	{
		bookLoginDialogView = (LinearLayout)View.inflate(context, R.layout.custom_book_login_dialog, null);
		book_auto_login_check = (CheckBox)bookLoginDialogView.findViewById(R.id.book_auto_login_check);
	}
	public LinearLayout getBookLoginDialogView() {
		return bookLoginDialogView;
	}
	public CheckBox getBook_auto_login_check() {
		return book_auto_login_check;
	}
	public LinearLayout getBook_login_layout() {
		return book_login_layout;
	}
}
