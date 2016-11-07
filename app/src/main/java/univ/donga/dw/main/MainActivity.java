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
        
        
        //�������� ���������� �����ѵ� �ʿ��Ѱ��� ����Ʈ�� �Ѱ��ش�.
        Intent intent = getIntent();
        dataManager.setCarteSocketInfo((CarteSocketInfo)(intent.getParcelableExtra("CARTE")));
        dataManager.setFreeBoardSocketInfo((FreeBoardSocketInfo)(intent.getParcelableExtra("FREEBOARD")));
        dataManager.setTaxiSocketInfo((TaxiSocketInfo)(intent.getParcelableExtra("TAXI")));

        //�޴��� �����ϰ�
        insertMainMenu();
        //����Ʈ�� �޾��ش�.
        viewManager.getMainList().setAdapter(new MainListAdapter(this, dataManager.getListArr()));
        viewManager.getMainList().setOnItemClickListener(new MainListClickEvent(this, viewManager, dataManager));
        //�α��ΰ� �α׾ƿ� ��ư�� �̺�Ʈ �޾��ش�.
        viewManager.getLogin_btn().setOnClickListener(new LoginButtonClickEvent(this));
        viewManager.getLogout_btn().setOnClickListener(new LogoutButtonClickEvent(this, viewManager, dataManager));
        
        //�ڵ��α��ο� ���� �α��ι�ư�� �α׾ƿ���ư�� �������ش�.
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
        
        //�ִϸ��̼��� �޾��ִ� �۾��� �Ѵ�.
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
		//���� AUTO LOGIN�� ���� ������ ��Ű������ ���� ������Ų��.
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
    		//�α��ο� �����ϸ�
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
    	dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_notice, "��������"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_brightness, "����Խ���"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_cart, "�������"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_batterylow, "�Ĵ�ǥ"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_lib, "����������Ȳ"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_help, "����������"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_profle, "�л���"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_time_table, "�ð�ǥ"));
        dataManager.getListArr().add(new MainListItem(R.drawable.app_icon_windy, "ķ�۽��ȳ�"));
    }
}