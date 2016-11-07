package univ.donga.dw.login.info;

import univ.donga.dw.service.CampusManagerService;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class DataManager {
	private Context context;
	private final String LOGIN_URL = "http://dalis.donga.ac.kr/m/mLogin.csp?HLOC=DALIS&Kor=1";
	//로그인후 html소스를 가지고오기위한 스크립트인터페이스
	private BookLoginJavaScriptInterface scrptInterface;
	
	public DataManager(Context context)
	{
		this.context = context;
		//쿠키 저장을위한 준비
		CookieSyncManager.createInstance(context);
		CookieSyncManager.getInstance().startSync();
	}
	public String getLOGIN_URL() {
		return LOGIN_URL;
	}
	public Boolean isAuto_login() {
		return CampusManagerService.auto_login;
	}
	public void setAuto_login(boolean auto_login) {
		CampusManagerService.auto_login = auto_login;
	}
	
	public void setScrptInterface(BookLoginJavaScriptInterface scrptInterface) {
		this.scrptInterface = scrptInterface;
	}
	public BookLoginJavaScriptInterface getScrptInterface() {
		return scrptInterface;
	}
}
