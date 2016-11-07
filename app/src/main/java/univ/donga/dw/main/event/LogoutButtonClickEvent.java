package univ.donga.dw.main.event;

import univ.donga.dw.login.BookLoginActivity;
import univ.donga.dw.main.info.DataManager;
import univ.donga.dw.main.info.ViewManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;

public class LogoutButtonClickEvent implements Button.OnClickListener{

	private Context context;
	private ViewManager viewManager;
	private DataManager dataManager;
	public LogoutButtonClickEvent(Context context, ViewManager viewManager, DataManager dataManager)
	{
		this.context = context;
		this.viewManager = viewManager;
		this.dataManager = dataManager;
	}
	public void onClick(View v) {
		//로그아웃버튼을 없애고 로그인 버튼을 달아준다.
		viewManager.getLogout_btn().setVisibility(View.GONE);
		viewManager.getLogin_btn().setVisibility(View.VISIBLE);
		dataManager.setAuto_login(false);
		//쿠키정보를 삭제한다.
		CookieSyncManager.getInstance().stopSync();
		CookieManager.getInstance().removeAllCookie();
		Log.e("error", "Delete Cookie!!");
	}
}
