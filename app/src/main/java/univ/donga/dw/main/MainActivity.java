package univ.donga.dw.main;

import univ.donga.dw.R;
import univ.donga.dw.info.socket.CarteSocketInfo;
import univ.donga.dw.info.socket.FreeBoardSocketInfo;
import univ.donga.dw.info.socket.TaxiSocketInfo;
import univ.donga.dw.main.event.LoginButtonClickEvent;
import univ.donga.dw.main.event.LogoutButtonClickEvent;
import univ.donga.dw.main.event.MainListClickEvent;
import univ.donga.dw.main.info.DataManager;
import univ.donga.dw.main.info.ViewManager;
import univ.donga.dw.thread.MarketDownThread;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class MainActivity extends Activity {
	private ViewManager viewManager;
	private DataManager dataManager;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewManager = new ViewManager(this);
        dataManager = new DataManager(this);
        
        new MarketDownThread(1).start();
        
        
        //세가지의 소켓정보를 저장한뒤 필요한곳에 인텐트로 넘겨준다.
        Intent intent = getIntent();
        dataManager.setCarteSocketInfo((CarteSocketInfo)(intent.getParcelableExtra("CARTE")));
        dataManager.setFreeBoardSocketInfo((FreeBoardSocketInfo)(intent.getParcelableExtra("FREEBOARD")));
        dataManager.setTaxiSocketInfo((TaxiSocketInfo)(intent.getParcelableExtra("TAXI")));

        //메뉴를 삽입하고
        insertMainMenu();
        //리스트를 달아준다.
        viewManager.getMainList().setAdapter(new MainListAdapter(this, dataManager.getListArr()));
        viewManager.getMainList().setOnItemClickListener(new MainListClickEvent(this, viewManager, dataManager));
        //로그인과 로그아웃 버튼에 이벤트 달아준다.
        viewManager.getLogin_btn().setOnClickListener(new LoginButtonClickEvent(this));
        viewManager.getLogout_btn().setOnClickListener(new LogoutButtonClickEvent(this, viewManager, dataManager));
        
        //자동로그인에 따라 로그인버튼과 로그아웃버튼을 설정해준다.
        if(dataManager.isAuto_login() == true)
        {
        	viewManager.getLogin_btn().setVisibility(View.GONE);
        	viewManager.getLogout_btn().setVisibility(View.VISIBLE);
        }
        else
        {
        	viewManager.getLogout_btn().setVisibility(View.GONE);
        	viewManager.getLogin_btn().setVisibility(View.VISIBLE);
        }
        
        //애니메이션을 달아주는 작업을 한다.
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);
        animation = new TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);    
        viewManager.getMainList().setLayoutAnimation(controller);
	}
    protected void onDestroy() {
		super.onDestroy();
		CookieSyncManager.createInstance(this);
		CookieSyncManager.getInstance().stopSync();
		//만약 AUTO LOGIN을 하지 않으면 쿠키정보를 전부 삭제시킨다.
		if(!dataManager.isAuto_login())
		{
			Log.e("error", "Delete Cookie!!");
			CookieManager.getInstance().removeAllCookie();
		}
	}
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	switch(requestCode)
    	{
    	case DataManager.LOGIN_RESULT :
    		//로그인에 성공하면
    		if(resultCode == RESULT_OK)
    		{
    			viewManager.getLogin_btn().setVisibility(View.GONE);
    			viewManager.getLogout_btn().setVisibility(View.VISIBLE);
    		}
    		break;
    	}
    }
    public void insertMainMenu()
    {
    	dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_notice, "공지사항"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_brightness, "취업게시판"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_cart, "벼룩시장"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_batterylow, "식단표"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_lib, "도서대출현황"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_help, "도서관열람"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_profle, "학생증"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_time_table, "시간표"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_windy, "캠퍼스안내"));
    }
}